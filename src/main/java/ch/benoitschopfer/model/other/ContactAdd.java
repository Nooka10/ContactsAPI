package ch.benoitschopfer.model.other;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

/**
 * ContactAdd
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2020-09-24T16:13:09.139748+02:00[Europe/Paris]")
public class ContactAdd {
  @JsonProperty("firstname")
  @NotNull
  @Size(min = 3, message = "Firstname should have at least 3 characters")
  private String firstname;

  @JsonProperty("lastname")
  @NotNull
  @Size(min = 3, message = "Lastname should have at least 3 characters")
  private String lastname;

  @JsonProperty("address")
  private String address;

  @JsonProperty("email")
  @NotNull
  @Email
  private String email;

  @JsonProperty("mobilephone")
  private String mobilephone;

  public ContactAdd firstname(String firstname) {
    this.firstname = firstname;
    return this;
  }

  /**
   * Get firstname
   * @return firstname
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull
  public String getFirstname() {
    return firstname;
  }

  public void setFirstname(String firstname) {
    this.firstname = firstname;
  }

  public ContactAdd lastname(String lastname) {
    this.lastname = lastname;
    return this;
  }

  /**
   * Get lastname
   * @return lastname
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull
  public String getLastname() {
    return lastname;
  }

  public void setLastname(String lastname) {
    this.lastname = lastname;
  }

  public ContactAdd address(String address) {
    this.address = address;
    return this;
  }

  /**
   * Get address
   * @return address
  */
  @ApiModelProperty(value = "")
  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public ContactAdd email(String email) {
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

  public ContactAdd mobilephone(String mobilephone) {
    this.mobilephone = mobilephone;
    return this;
  }

  /**
   * Get mobilephone
   * @return mobilephone
  */
  @ApiModelProperty(value = "")
  public String getMobilephone() {
    return mobilephone;
  }

  public void setMobilephone(String mobilephone) {
    this.mobilephone = mobilephone;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ContactAdd contactAdd = (ContactAdd) o;
    return Objects.equals(this.firstname, contactAdd.firstname) &&
           Objects.equals(this.lastname, contactAdd.lastname) &&
           Objects.equals(this.address, contactAdd.address) &&
           Objects.equals(this.email, contactAdd.email) &&
           Objects.equals(this.mobilephone, contactAdd.mobilephone);
  }

  @Override
  public int hashCode() {
    return Objects.hash(firstname, lastname, address, email, mobilephone);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ContactAdd {\n");
    sb.append("    firstname: ").append(toIndentedString(firstname)).append("\n");
    sb.append("    lastname: ").append(toIndentedString(lastname)).append("\n");
    sb.append("    address: ").append(toIndentedString(address)).append("\n");
    sb.append("    email: ").append(toIndentedString(email)).append("\n");
    sb.append("    mobilephone: ").append(toIndentedString(mobilephone)).append("\n");
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

