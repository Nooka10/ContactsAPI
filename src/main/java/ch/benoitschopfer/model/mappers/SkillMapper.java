package ch.benoitschopfer.model.mappers;

import ch.benoitschopfer.model.DTO.SkillAddOrUpdate;
import ch.benoitschopfer.model.Skill;
import org.mapstruct.Mapper;

@Mapper
public interface SkillMapper {
  Skill skillAddToSkill(SkillAddOrUpdate skillAddOrUpdate);

  SkillAddOrUpdate skillToSkillAdd(Skill skill);

  Skill skillUpdateToSkill(SkillAddOrUpdate skillAddOrUpdate);

  SkillAddOrUpdate skillToSkillUpdate(Skill skill);
}
