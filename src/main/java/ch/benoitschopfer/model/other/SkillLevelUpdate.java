package ch.benoitschopfer.model.other;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * SkillLevelUpdate
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2020-09-24T16:13:09.139748+02:00[Europe/Paris]")
public class SkillLevelUpdate {
  @JsonProperty("level")
  @NotNull
  @Min(1)
  @Max(10)
  private long level;

  public SkillLevelUpdate level(long level) {
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


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SkillLevelUpdate skillLevelUpdate = (SkillLevelUpdate) o;
    return Objects.equals(this.level, skillLevelUpdate.level);
  }

  @Override
  public int hashCode() {
    return Objects.hash(level);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SkillLevelUpdate {\n");

    sb.append("    level: ").append(toIndentedString(level)).append("\n");
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

