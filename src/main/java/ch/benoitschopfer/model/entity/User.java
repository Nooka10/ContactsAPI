package ch.benoitschopfer.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * User
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2020-09-24T16:13:09.139748+02:00[Europe/Paris]")
@Entity
public class User extends RepresentationModel<User> {
  @JsonProperty("id")
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @JsonProperty("email")
  @Column(unique = true)
  @NotNull
  @NotBlank
  @Email
  @Size(min = 3, max = 30, message = "Email should have at least 3 characters")
  private String email;

  @JsonIgnore
  @NotNull
  @NotBlank
  @Size(min = 3, message = "Password should have at least 3 characters")
  private String password;

  @JsonProperty("contacts")
  @Valid
  @OneToMany(mappedBy = "linkedUser")
  @OnDelete(action = OnDeleteAction.CASCADE)
  private List<Contact> contacts = new ArrayList<>();

  @JsonProperty("roles")
  @NotNull
  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "user_roles",
    joinColumns = @JoinColumn(name = "user_id", nullable = false),
    inverseJoinColumns = @JoinColumn(name = "role_id", nullable = false))
  private Set<Role> roles = new HashSet<>();

  public User() {}

  public long getId() {
    return id;
  }

  public String getEmail() {
    return email;
  }

  public String getPassword() {
    return password;
  }

  public List<Contact> getContacts() {
    return contacts;
  }

  public Set<Role> getRoles() {
    return roles;
  }

  public void setId(long id) {
    this.id = id;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setPassword(String password) {
    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    this.password = bCryptPasswordEncoder.encode(password);
  }

  public void addRole(Role role) {
    if (!this.roles.contains(role)) {
      this.roles.add(role);
    }
  }

  public void addContact(Contact contact) {
    if (!this.contacts.contains(contact)) {
      this.contacts.add(contact);
    }
  }

  public void removeContact(Contact contact) {
    if (this.contacts.contains(contact)) {
      this.contacts.remove(contact);
    }
  }
}

