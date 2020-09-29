package ch.benoitschopfer.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Skill
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2020-09-24T16:13:09.139748+02:00[Europe/Paris]")
@Entity
public class Skill extends RepresentationModel<Skill> {
  @JsonProperty("id")
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @JsonProperty("name")
  @NotNull
  @Size(min = 3, message = "Name should have at least 3 characters")
  @Column(unique = true)
  private String name;

  @JsonProperty("usersLevels")
  @Valid
  @OneToMany(mappedBy = "skill")
  private List<SkillLevel> usersLevels = new ArrayList<>();

  public Skill id(long id) {
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

  public Skill name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   * @return name
  */
  @ApiModelProperty(example = "SpringBoot", required = true, value = "")
  @NotNull
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Skill usersLevels(List<SkillLevel> usersLevels) {
    this.usersLevels = usersLevels;
    return this;
  }

  public Skill addUsersLevelsItem(SkillLevel usersLevelsItem) {
    this.usersLevels.add(usersLevelsItem);
    return this;
  }

  /**
   * Get usersLevels
   * @return usersLevels
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull
  @Valid
  public List<SkillLevel> getUsersLevels() {
    return usersLevels;
  }

  public void setUsersLevels(List<SkillLevel> usersLevels) {
    this.usersLevels = usersLevels;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Skill skill = (Skill) o;
    return Objects.equals(this.id, skill.id) &&
           Objects.equals(this.name, skill.name) &&
           Objects.equals(this.usersLevels, skill.usersLevels);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, usersLevels);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Skill {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    usersLevels: ").append(toIndentedString(usersLevels)).append("\n");
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

