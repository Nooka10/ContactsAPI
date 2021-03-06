package ch.benoitschopfer.repository;

import ch.benoitschopfer.model.entity.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Optional;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Long>, JpaSpecificationExecutor<Skill>, CrudRepository<Skill, Long> {
  Optional<Skill> findByName(@NotNull @Size(min = 3, message = "Name should have at least 3 characters") String name);
}
