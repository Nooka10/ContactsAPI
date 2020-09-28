package ch.benoitschopfer.repository;

import ch.benoitschopfer.model.Contact;
import ch.benoitschopfer.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

public interface ContactRepository extends JpaRepository<Contact, Long>, JpaSpecificationExecutor<Contact>, CrudRepository<Contact, Long> {
}
