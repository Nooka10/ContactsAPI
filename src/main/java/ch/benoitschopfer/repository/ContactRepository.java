package ch.benoitschopfer.repository;

import ch.benoitschopfer.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.math.BigDecimal;

public interface ContactRepository extends JpaRepository<Contact, BigDecimal>, CrudRepository<Contact, BigDecimal> {
}
