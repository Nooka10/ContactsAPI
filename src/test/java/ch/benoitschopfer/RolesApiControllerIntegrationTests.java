package ch.benoitschopfer;

import ch.benoitschopfer.model.entity.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(
  webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@TestPropertySource(
  properties = {
    "MYSQL_DATABASE=contactsapiTESTS"
  }
)
public class RolesApiControllerIntegrationTests {

  @LocalServerPort
  private int port;

  private final String baseUrl = "http://localhost:";

  @Autowired
  private TestRestTemplate restTemplate;

  private String createUrl(String endpointURL) {
    return baseUrl + port + "/api/secured" + endpointURL;
  }

  @Test
  @Sql(scripts = {"/roleTests/data.sql"})
  public void anAdminCanGetAllRoles() {
    ParameterizedTypeReference<RestResponsePage<Role>> responseType = new ParameterizedTypeReference<>() { };

    ResponseEntity<RestResponsePage<Role>> responseEntity = this.restTemplate
      .withBasicAuth("admin@owt.ch", "admin")
      .exchange(createUrl("/roles"), HttpMethod.GET, null, responseType);

    assertEquals(200, responseEntity.getStatusCodeValue());
    assertNotNull(responseEntity.getBody());
    Role ADMIN = responseEntity.getBody().getContent().get(0);
    Role USER = responseEntity.getBody().getContent().get(1);
    assertEquals(ADMIN.getName(), "ROLE_ADMIN");
    assertEquals(USER.getName(), "ROLE_USER");
  }

  @Test
  @Sql(scripts = {"/roleTests/data.sql"})
  public void anUserCannotGetAllRoles() {
    ResponseEntity<String> responseEntity = this.restTemplate
      .withBasicAuth("user@owt.ch", "user")
      .exchange(createUrl("/roles"), HttpMethod.GET, null, String.class);

    assertEquals(401, responseEntity.getStatusCodeValue());
  }
}
