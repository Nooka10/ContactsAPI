package ch.benoitschopfer.model.mappers;

import ch.benoitschopfer.model.entity.Contact;
import ch.benoitschopfer.model.other.ContactAdd;
import ch.benoitschopfer.model.other.ContactUpdate;
import org.mapstruct.Mapper;

@Mapper
public interface ContactMapper {
  Contact contactAddToContact(ContactAdd contactAdd);

  ContactAdd contactToContactAdd(Contact contact);

  Contact contactUpdateToContact(ContactUpdate contactUpdate);

  ContactUpdate contactToContactUpdate(Contact contact);
}
