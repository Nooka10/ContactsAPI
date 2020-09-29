package ch.benoitschopfer.repository;

import ch.benoitschopfer.model.entity.Skill;
import ch.benoitschopfer.model.entity.SkillLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SkillLevelRepository extends JpaRepository<SkillLevel, Long>, JpaSpecificationExecutor<SkillLevel>, CrudRepository<SkillLevel, Long> {
  Optional<SkillLevel> findBySkill(Skill skill);
}
