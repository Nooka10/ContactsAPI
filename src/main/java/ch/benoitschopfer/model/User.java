package ch.benoitschopfer.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * User
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2020-09-24T16:13:09.139748+02:00[Europe/Paris]")
@Entity
public class User {
  @JsonProperty("id")
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private BigDecimal id;

  @JsonProperty("email")
  @Column(unique = true)
  @NotNull
  @Email
  @Size(min = 3, message = "Email should have at least 3 characters")
  private String email;

  @JsonProperty("password")
  @NotNull
  @Size(min = 3, message = "Password should have at least 3 characters")
  private String password;

  @JsonProperty("contacts")
  @Valid
  @OneToMany(mappedBy = "linkedUser")
  private List<Contact> contacts = new ArrayList<>();

  @JsonProperty("rights")
  @NotNull
  private String rights;

  public User id(BigDecimal id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
   */
  @ApiModelProperty(required = true, value = "")
  @NotNull
  public BigDecimal getId() {
    return id;
  }

  public void setId(BigDecimal id) {
    this.id = id;
  }

  public User email(String email) {
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

  public User password(String password) {
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

  public User contacts(List<Contact> contacts) {
    this.contacts = contacts;
    return this;
  }

  public User addContactsItem(Contact contactItem) {
    this.contacts.add(contactItem);
    return this;
  }

  public User removeContactsItem(Contact contactItem) {
    this.contacts.remove(contactItem);
    return this;
  }

  /**
   * Get contacts
   * @return contacts
   */
  @ApiModelProperty(required = true, value = "")
  @NotNull
  @Valid
  public List<Contact> getContacts() {
    return contacts;
  }

  public void setContacts(List<Contact> contacts) {
    this.contacts = contacts;
  }

  public User rights(String rights) {
    this.rights = rights;
    return this;
  }

  /**
   * Get rights
   * @return rights
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull
  public String getRights() {
    return rights;
  }

  public void setRights(String rights) {
    this.rights = rights;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    User user = (User) o;
    return Objects.equals(this.id, user.id) &&
           Objects.equals(this.email, user.email) &&
           Objects.equals(this.password, user.password) &&
           Objects.equals(this.contacts, user.contacts) &&
           Objects.equals(this.rights, user.rights);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, email, password, contacts, rights);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class User {\n");

    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    email: ").append(toIndentedString(email)).append("\n");
    sb.append("    password: ").append(toIndentedString(password)).append("\n");
    sb.append("    contacts: ").append(toIndentedString(contacts)).append("\n");
    sb.append("    rights: ").append(toIndentedString(rights)).append("\n");
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

