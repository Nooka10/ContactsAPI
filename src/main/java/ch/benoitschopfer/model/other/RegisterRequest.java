package ch.benoitschopfer.model.other;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Set;

/**
 * UserUpdate
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2020-09-24T16:13:09.139748+02:00[Europe/Paris]")
public class RegisterRequest implements Serializable {
  @ApiModelProperty(example = "admin@owt.ch / user1@owt.ch / user2@owt.ch")
  @JsonProperty("email")
  @NotNull
  @Email
  @Size(min = 3, message = "Email should have at least 3 characters")
  private String email;

  @ApiModelProperty(example = "admin / user / user")
  @JsonProperty("password")
  @NotNull
  @Size(min = 3, message = "Password should have at least 3 characters")
  private String password;

  // Added only to allow you to set easilly the user role
  private Set<EnumRoles> role;

  public RegisterRequest() {}

  // Used for tests
  public RegisterRequest(@NotNull @Email @Size(min = 3, message = "Email should have at least 3 characters") String email,
                         @NotNull @Size(min = 3, message = "Password should have at least 3 characters") String password,
                         Set<EnumRoles> role) {
    this.email = email;
    this.password = password;
    this.role = role;
  }

  public String getEmail() {
    return email;
  }

  public String getPassword() {
    return password;
  }

  public Set<EnumRoles> getRole() {
    return this.role;
  }
}

