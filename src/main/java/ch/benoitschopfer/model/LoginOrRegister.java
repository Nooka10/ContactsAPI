package ch.benoitschopfer.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * LoginOrRegister
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2020-09-24T14:30:44.095816+02:00[Europe/Paris]")
public class LoginOrRegister {
  @JsonProperty("email")
  private String email;

  @JsonProperty("password")
  private String password;

  public LoginOrRegister email(String email) {
    this.email = email;
    return this;
  }

  /**
   * Get email
   * @return email
   */
  @ApiModelProperty(required = true, value = "")
  @NotNull
  @Email
  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public LoginOrRegister password(String password) {
    this.password = password;
    return this;
  }

  /**
   * Get password
   * @return password
   */
  @ApiModelProperty(required = true, value = "")
  @NotNull
  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    LoginOrRegister loginOrRegister = (LoginOrRegister) o;
    return Objects.equals(this.email, loginOrRegister.email) &&
           Objects.equals(this.password, loginOrRegister.password);
  }

  @Override
  public int hashCode() {
    return Objects.hash(email, password);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class LoginOrRegister {\n");
    sb.append("    email: ").append(toIndentedString(email)).append("\n");
    sb.append("    password: ").append(toIndentedString(password)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

