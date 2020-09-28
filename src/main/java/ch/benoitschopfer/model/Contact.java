package ch.benoitschopfer.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Contact
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2020-09-24T16:13:09.139748+02:00[Europe/Paris]")
@Entity
public class Contact {
  @JsonProperty("id")
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
private Integer id;

  @JsonProperty("firstname")
  @NotNull
  @Size(min = 3, message = "Firstname should have at least 3 characters")
  private String firstname;

  @JsonProperty("lastname")
  @NotNull
  @Size(min = 3, message = "Lastname should have at least 3 characters")
  private String lastname;

  @JsonProperty("fullname")
  private String fullname;

  @JsonProperty("address")
  private String address;

  @JsonProperty("email")
  @NotNull
  @Email
  private String email;

  @JsonProperty("mobilephone")
  private String mobilephone;

  @JsonProperty("linkedUser")
  @ManyToOne
  @JoinColumn
  private User linkedUser;

  @JsonProperty("skills")
  @Valid
  @OneToMany(mappedBy = "skilledContact")
  private List<SkillLevel> skills = new ArrayList<>();

  public Contact id(Integer id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
   */
  @ApiModelProperty(required = true, value = "")
  @NotNull
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Contact firstname(String firstname) {
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

  public Contact lastname(String lastname) {
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

  public Contact fullname(String fullname) {
    this.fullname = fullname;
    return this;
  }

  /**
   * Get fullname
   * @return fullname
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull
  public String getFullname() {
    return fullname;
  }

  public void setFullname(String fullname) {
    this.fullname = fullname;
  }

  public Contact address(String address) {
    this.address = address;
    return this;
  }

  /**
   * Get address
   * @return address
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull
  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public Contact email(String email) {
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

  public Contact mobilephone(String mobilephone) {
    this.mobilephone = mobilephone;
    return this;
  }

  /**
   * Get mobilephone
   * @return mobilephone
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull
  public String getMobilephone() {
    return mobilephone;
  }

  public void setMobilephone(String mobilephone) {
    this.mobilephone = mobilephone;
  }

  public Contact linkedUser(User linkedUser) {
    this.linkedUser = linkedUser;
    return this;
  }

  /**
   * Get linkedUser
   * @return linkedUser
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull
  @Valid
  public User getLinkedUser() {
    return linkedUser;
  }

  public void setLinkedUser(User linkedUser) {
    this.linkedUser = linkedUser;
  }

  public Contact skills(List<SkillLevel> skills) {
    this.skills = skills;
    return this;
  }

  public Contact addSkillsItem(SkillLevel skillsItem) {
    this.skills.add(skillsItem);
    return this;
  }

  /**
   * Get skills
   * @return skills
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull
  @Valid
  public List<SkillLevel> getSkills() {
    return skills;
  }

  public void setSkills(List<SkillLevel> skills) {
    this.skills = skills;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Contact contact = (Contact) o;
    return Objects.equals(this.id, contact.id) &&
           Objects.equals(this.firstname, contact.firstname) &&
           Objects.equals(this.lastname, contact.lastname) &&
           Objects.equals(this.fullname, contact.fullname) &&
           Objects.equals(this.address, contact.address) &&
           Objects.equals(this.email, contact.email) &&
           Objects.equals(this.mobilephone, contact.mobilephone) &&
           Objects.equals(this.linkedUser, contact.linkedUser) &&
           Objects.equals(this.skills, contact.skills);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, firstname, lastname, fullname, address, email, mobilephone, linkedUser, skills);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Contact {\n");

    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    firstname: ").append(toIndentedString(firstname)).append("\n");
    sb.append("    lastname: ").append(toIndentedString(lastname)).append("\n");
    sb.append("    fullname: ").append(toIndentedString(fullname)).append("\n");
    sb.append("    address: ").append(toIndentedString(address)).append("\n");
    sb.append("    email: ").append(toIndentedString(email)).append("\n");
    sb.append("    mobilephone: ").append(toIndentedString(mobilephone)).append("\n");
    sb.append("    linkedUser: ").append(toIndentedString(linkedUser)).append("\n");
    sb.append("    skills: ").append(toIndentedString(skills)).append("\n");
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

