package ch.benoitschopfer;

import ch.benoitschopfer.model.entity.User;
import ch.benoitschopfer.model.other.EnumRoles;
import ch.benoitschopfer.model.other.JwtResponse;
import ch.benoitschopfer.model.other.LoginRequest;
import ch.benoitschopfer.model.other.RegisterRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(
  webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@TestPropertySource(
  properties = {
    "MYSQL_DATABASE=contactsapiTESTS"
  }
)
public class AuthenticationApiControllerIntegrationTests {

  @LocalServerPort
  private int port;

  private final String baseUrl = "http://localhost:";

  @Autowired
  private TestRestTemplate restTemplate;

  private String createUrl(String endpointURL) {
    return baseUrl + port + "/api" + endpointURL;
  }

  @Test
  @Sql(scripts = {"/authenticationTests/register.sql"})
  public void registerANewAdmin() {
    RegisterRequest registerRequest = new RegisterRequest("admin@owt.ch", "admin", Set.of(EnumRoles.ROLE_ADMIN));

    ResponseEntity<JwtResponse> responseEntity = this.restTemplate.postForEntity(createUrl("/auth/register"), registerRequest, JwtResponse.class);
    assertEquals(201, responseEntity.getStatusCodeValue());
    assertNotNull(responseEntity.getBody());
    User newUser = responseEntity.getBody().getUser();
    assertTrue(newUser.isAdmin());
    assertEquals("admin@owt.ch", newUser.getEmail());
    assertTrue(newUser.getContacts().isEmpty());
    assertFalse(newUser.getRoles().isEmpty());
    assertEquals(EnumRoles.ROLE_ADMIN.toString(), newUser.getRoles().get(0).getName());
  }

  @Test
  @Sql(scripts = {"/authenticationTests/register.sql"})
  public void registerANewUser() {
    RegisterRequest registerRequest = new RegisterRequest("user@owt.ch", "user", Set.of(EnumRoles.ROLE_USER));

    ResponseEntity<JwtResponse> responseEntity = this.restTemplate.postForEntity(createUrl("/auth/register"), registerRequest, JwtResponse.class);
    assertEquals(201, responseEntity.getStatusCodeValue());
    assertNotNull(responseEntity.getBody());
    User newUser = responseEntity.getBody().getUser();
    assertFalse(newUser.isAdmin());
    assertEquals("user@owt.ch", newUser.getEmail());
    assertTrue(newUser.getContacts().isEmpty());
    assertFalse(newUser.getRoles().isEmpty());
    assertEquals(EnumRoles.ROLE_USER.toString(), newUser.getRoles().get(0).getName());
  }

  @Test
  @Sql(scripts = {"/authenticationTests/register.sql"})
  public void failRegisterANewUserBecauseEmailAlreadyUsed() {
    RegisterRequest registerRequest = new RegisterRequest("user@owt.ch", "user", Set.of(EnumRoles.ROLE_USER));

    ResponseEntity<JwtResponse> responseEntityOk = this.restTemplate.postForEntity(createUrl("/auth/register"), registerRequest, JwtResponse.class);
    assertEquals(201, responseEntityOk.getStatusCodeValue());

    ResponseEntity<String> responseEntityError = this.restTemplate.postForEntity(createUrl("/auth/register"), registerRequest, String.class);
    assertEquals(409, responseEntityError.getStatusCodeValue());
    assertNotNull(responseEntityError.getBody());
    assertEquals("Error: This email is already used!", responseEntityError.getBody());
  }

  @Test
  @Sql(scripts = {"/authenticationTests/login.sql"})
  public void loginAsAdmin() {
    LoginRequest loginRequest = new LoginRequest("admin@owt.ch", "admin");

    ResponseEntity<JwtResponse> responseEntity = this.restTemplate.postForEntity(createUrl("/auth/login"), loginRequest, JwtResponse.class);
    assertEquals(200, responseEntity.getStatusCodeValue());
    assertNotNull(responseEntity.getBody());
    assertEquals("Bearer", responseEntity.getBody().getTokenType());
    User user = responseEntity.getBody().getUser();

    assertEquals(1, user.getId());
    assertEquals("admin@owt.ch", user.getEmail());
    assertTrue(user.isAdmin());

    ResponseEntity<String> logout = this.restTemplate.postForEntity(createUrl("/auth/logout"), null, String.class);
    assertEquals(204, logout.getStatusCodeValue());
  }

  @Test
  @Sql(scripts = {"/authenticationTests/login.sql"})
  public void loginAsUser() {
    LoginRequest loginRequest = new LoginRequest("user1@owt.ch", "user");

    ResponseEntity<JwtResponse> responseEntity = this.restTemplate.postForEntity(createUrl("/auth/login"), loginRequest, JwtResponse.class);
    assertEquals(200, responseEntity.getStatusCodeValue());
    assertNotNull(responseEntity.getBody());
    assertEquals("Bearer", responseEntity.getBody().getTokenType());
    User user = responseEntity.getBody().getUser();

    assertEquals(2, user.getId());
    assertEquals("user1@owt.ch", user.getEmail());
    assertFalse(user.isAdmin());

    ResponseEntity<String> logout = this.restTemplate.postForEntity(createUrl("/auth/logout"), null, String.class);
    assertEquals(204, logout.getStatusCodeValue());
  }
}
