package ch.benoitschopfer.repository;

import ch.benoitschopfer.model.Skill;
import ch.benoitschopfer.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

public interface SkillRepository extends JpaRepository<Skill, Long>, JpaSpecificationExecutor<Skill>, CrudRepository<Skill, Long> {

}
