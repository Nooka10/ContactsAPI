package ch.benoitschopfer.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.lang.Nullable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.*;

/**
 * User
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2020-09-24T16:13:09.139748+02:00[Europe/Paris]")
@Entity
public class User {
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
  @JsonManagedReference(value = "linkedUser")
  @Valid
  @OneToMany(mappedBy = "linkedUser", targetEntity = Contact.class, fetch = FetchType.LAZY)
  @OnDelete(action = OnDeleteAction.CASCADE)
  private List<Contact> contacts = new ArrayList<>();

  @JsonProperty("roles")
  @NotNull
  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "user_roles",
    joinColumns = @JoinColumn(name = "user_id", nullable = false),
    inverseJoinColumns = @JoinColumn(name = "role_id", nullable = false))
  private List<Role> roles = new ArrayList<>();

  public User() {}

  // Contructor used for testing
  public User(long id,
               @NotNull @NotBlank @Email @Size(min = 3, max = 30, message = "Email should have at least 3 characters") String email,
               @NotNull @NotBlank @Size(min = 3, message = "Password should have at least 3 characters") String password,
               @Nullable @Valid List<Contact> contacts,
               @Nullable List<Role> roles) {
    this.id = id;
    this.email = email;
    this.password = password; // new BCryptPasswordEncoder().encode(password);
    if (contacts != null && !contacts.isEmpty()) {
      this.contacts = contacts;
    }
    if (roles != null && !roles.isEmpty()) {
      this.roles = roles;
    } else {
      this.roles.add(new Role("ROLE_USER"));
    }
  }

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

  public List<Role> getRoles() {
    return roles;
  }

  public boolean isAdmin() {
    for (Role role : this.roles) {
      if (role.isAdmin()) {
        return true;
      }
    }
    return false;
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

  public void addRole(@NotNull @Valid Role role) {
    if (!this.roles.contains(role)) {
      this.roles.add(role);
    }
  }

  public void addContact(@NotNull @Valid Contact contact) {
    if (!this.contacts.contains(contact)) {
      this.contacts.add(contact);
    }
  }

  public Optional<Contact> getContactById(Long id) {
    for (Contact contact : this.contacts) {
      if (contact.getId() == id) {
        return Optional.of(contact);
      }
    }
    return Optional.empty();
  }

  public void removeContact(Contact contact) {
    if (this.contacts.contains(contact)) {
      this.contacts.remove(contact);
    }
  }

  public boolean removeContactById(Long id) {
    for (Contact contact : this.contacts) {
      if (contact.getId() == id) {
        return true;
      }
    }
    return false;
  }
}

