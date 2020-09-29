package ch.benoitschopfer.model.entity;

import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Role extends RepresentationModel<Role> {
  public static final String USER = "ROLE_USER";
  public static final String ADMIN = "ROLE_ADMIN";

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String name;

  // bi-directional many-to-many association to User
  @ManyToMany(mappedBy = "roles")
  private Set<User> users;

  public Role() {}

  public Role(String name) {
    this.name = name;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  private Set<User> getUsers() {
    return users;
  }

  private void setUsers(Set<User> users) {
    this.users = users;
  }
}
