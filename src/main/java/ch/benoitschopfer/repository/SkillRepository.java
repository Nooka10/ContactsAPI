package ch.benoitschopfer.repository;

import ch.benoitschopfer.model.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface SkillRepository extends JpaRepository<Skill, Long>, CrudRepository<Skill, Long> {

}
