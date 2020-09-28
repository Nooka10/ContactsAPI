package ch.benoitschopfer.model.mappers;

import ch.benoitschopfer.model.DTO.SkillLevelAdd;
import ch.benoitschopfer.model.DTO.SkillLevelUpdate;
import ch.benoitschopfer.model.SkillLevel;
import org.mapstruct.Mapper;

@Mapper
public interface SkillLevelMapper {
  SkillLevel SkillLevelAddToSkillLevel(SkillLevelAdd skillLevelAdd);

  SkillLevelAdd skillLevelToSkillLevelAdd(SkillLevel skillLevel);

  SkillLevel SkillLevelUpdateToSkillLevel(SkillLevelUpdate skillLevelUpdate);

  SkillLevelUpdate skillLevelToSkillLevelUpdate(SkillLevel skillLevel);
}
