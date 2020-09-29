package ch.benoitschopfer.model.mappers;

import ch.benoitschopfer.model.entity.Skill;
import ch.benoitschopfer.model.other.SkillAddOrUpdate;
import org.mapstruct.Mapper;

@Mapper
public interface SkillMapper {
  Skill skillAddToSkill(SkillAddOrUpdate skillAddOrUpdate);

  SkillAddOrUpdate skillToSkillAdd(Skill skill);

  Skill skillUpdateToSkill(SkillAddOrUpdate skillAddOrUpdate);

  SkillAddOrUpdate skillToSkillUpdate(Skill skill);
}
