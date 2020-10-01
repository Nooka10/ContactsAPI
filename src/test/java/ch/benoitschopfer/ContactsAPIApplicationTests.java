package ch.benoitschopfer;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest()
@TestPropertySource(
  properties = {
    "MYSQL_DATABASE=contactsapiTESTS"
  }
)
class ContactsAPIApplicationTests {

    @Test
    void contextLoads() {
    }
}
