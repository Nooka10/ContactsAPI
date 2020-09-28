package ch.benoitschopfer.model.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * ContactToUpdate
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2020-09-24T16:13:09.139748+02:00[Europe/Paris]")
public class ContactToUpdate   {
  @JsonProperty("id")
  private long id;

  @JsonProperty("firstname")
  private String firstname;

  @JsonProperty("lastname")
  private String lastname;

  @JsonProperty("address")
  private String address;

  @JsonProperty("email")
  private String email;

  @JsonProperty("mobilephone")
  private String mobilephone;

  public ContactToUpdate id(long id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public ContactToUpdate firstname(String firstname) {
    this.firstname = firstname;
    return this;
  }

  /**
   * Get firstname
   * @return firstname
  */
  @ApiModelProperty(value = "")


  public String getFirstname() {
    return firstname;
  }

  public void setFirstname(String firstname) {
    this.firstname = firstname;
  }

  public ContactToUpdate lastname(String lastname) {
    this.lastname = lastname;
    return this;
  }

  /**
   * Get lastname
   * @return lastname
  */
  @ApiModelProperty(value = "")


  public String getLastname() {
    return lastname;
  }

  public void setLastname(String lastname) {
    this.lastname = lastname;
  }

  public ContactToUpdate address(String address) {
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

  public ContactToUpdate email(String email) {
    this.email = email;
    return this;
  }

  /**
   * Get email
   * @return email
  */
  @ApiModelProperty(value = "")

@javax.validation.constraints.Email
  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public ContactToUpdate mobilephone(String mobilephone) {
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
    ContactToUpdate contactToUpdate = (ContactToUpdate) o;
    return Objects.equals(this.id, contactToUpdate.id) &&
        Objects.equals(this.firstname, contactToUpdate.firstname) &&
        Objects.equals(this.lastname, contactToUpdate.lastname) &&
        Objects.equals(this.address, contactToUpdate.address) &&
        Objects.equals(this.email, contactToUpdate.email) &&
        Objects.equals(this.mobilephone, contactToUpdate.mobilephone);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, firstname, lastname, address, email, mobilephone);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ContactToUpdate {\n");

    sb.append("    id: ").append(toIndentedString(id)).append("\n");
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

