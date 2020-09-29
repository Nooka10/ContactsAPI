package ch.benoitschopfer.model.mappers;

import ch.benoitschopfer.model.entity.SkillLevel;
import ch.benoitschopfer.model.other.SkillLevelAdd;
import ch.benoitschopfer.model.other.SkillLevelUpdate;
import org.mapstruct.Mapper;

@Mapper
public interface SkillLevelMapper {
  SkillLevel SkillLevelAddToSkillLevel(SkillLevelAdd skillLevelAdd);

  SkillLevelAdd skillLevelToSkillLevelAdd(SkillLevel skillLevel);

  SkillLevel SkillLevelUpdateToSkillLevel(SkillLevelUpdate skillLevelUpdate);

  SkillLevelUpdate skillLevelToSkillLevelUpdate(SkillLevel skillLevel);
}
