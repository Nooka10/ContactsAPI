package ch.benoitschopfer.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * SkillLevel
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2020-09-24T16:13:09.139748+02:00[Europe/Paris]")
@Entity
public class SkillLevel extends RepresentationModel<SkillLevel> {
  @JsonProperty("id")
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @JsonProperty("skill")
  @ManyToOne
  @JoinColumn(name = "skill_id", referencedColumnName = "id", nullable = false)
  @OnDelete(action = OnDeleteAction.NO_ACTION)
  private Skill skill;

  @JsonProperty("level")
  private long level;

  @JsonProperty("skilledContact")
  @ManyToOne
  @JoinColumn(name = "contact_id", referencedColumnName = "id", nullable = false)
  @OnDelete(action = OnDeleteAction.NO_ACTION)
  private Contact skilledContact;

  public SkillLevel id(long id) {
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

  public SkillLevel skill(Skill skill) {
    this.skill = skill;
    return this;
  }

  /**
   * Get skill
   * @return skill
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull
  @Valid
  public Skill getSkill() {
    return skill;
  }

  public void setSkill(Skill skill) {
    this.skill = skill;
  }

  public SkillLevel level(long level) {
    this.level = level;
    return this;
  }

  /**
   * Get level
   * @return level
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull
  @Valid
  public long getLevel() {
    return level;
  }

  public void setLevel(long level) {
    this.level = level;
  }

  public SkillLevel skilledContact(Contact skilledContact) {
    this.skilledContact = skilledContact;
    return this;
  }

  /**
   * Get skilledContact
   * @return skilledContact
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull
  @Valid
  public Contact getSkilledContact() {
    return skilledContact;
  }

  public void setSkilledContact(Contact skilledContact) {
    this.skilledContact = skilledContact;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SkillLevel skillLevel = (SkillLevel) o;
    return Objects.equals(this.id, skillLevel.id) &&
           Objects.equals(this.skill, skillLevel.skill) &&
           Objects.equals(this.level, skillLevel.level) &&
           Objects.equals(this.skilledContact, skillLevel.skilledContact);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, skill, level, skilledContact);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SkillLevel {\n");

    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    skill: ").append(toIndentedString(skill)).append("\n");
    sb.append("    level: ").append(toIndentedString(level)).append("\n");
    sb.append("    skilledContact: ").append(toIndentedString(skilledContact)).append("\n");
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

