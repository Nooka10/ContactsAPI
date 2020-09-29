package ch.benoitschopfer.model.other;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * UserUpdate
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2020-09-24T16:13:09.139748+02:00[Europe/Paris]")
public class UserUpdate implements Serializable {
  @JsonProperty("email")
  @NotNull
  @Email
  @Size(min = 3, message = "Email should have at least 3 characters")
  private String email;

  @JsonProperty("password")
  @NotNull
  @Size(min = 3, message = "Password should have at least 3 characters")
  private String password;

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}

