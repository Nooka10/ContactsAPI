package ch.benoitschopfer.repository;

import ch.benoitschopfer.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User>, CrudRepository<User, Long> {
}
