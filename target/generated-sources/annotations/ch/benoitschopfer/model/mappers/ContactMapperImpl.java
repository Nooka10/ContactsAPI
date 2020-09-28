package ch.benoitschopfer.model.mappers;

import ch.benoitschopfer.model.entity.Contact;
import ch.benoitschopfer.model.other.ContactAdd;
import ch.benoitschopfer.model.other.ContactUpdate;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-09-30T23:39:22+0200",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 11.0.8 (AdoptOpenJDK)"
)
public class ContactMapperImpl implements ContactMapper {

    @Override
    public Contact contactAddToContact(ContactAdd contactAdd) {
        if ( contactAdd == null ) {
            return null;
        }

        Contact contact = new Contact();

        contact.setFirstname( contactAdd.getFirstname() );
        contact.setLastname( contactAdd.getLastname() );
        contact.setAddress( contactAdd.getAddress() );
        contact.setEmail( contactAdd.getEmail() );
        contact.setMobilephone( contactAdd.getMobilephone() );

        return contact;
    }

    @Override
    public ContactAdd contactToContactAdd(Contact contact) {
        if ( contact == null ) {
            return null;
        }

        ContactAdd contactAdd = new ContactAdd();

        contactAdd.setFirstname( contact.getFirstname() );
        contactAdd.setLastname( contact.getLastname() );
        contactAdd.setAddress( contact.getAddress() );
        contactAdd.setEmail( contact.getEmail() );
        contactAdd.setMobilephone( contact.getMobilephone() );

        return contactAdd;
    }

    @Override
    public Contact contactUpdateToContact(ContactUpdate contactUpdate) {
        if ( contactUpdate == null ) {
            return null;
        }

        Contact contact = new Contact();

        contact.setId( contactUpdate.getId() );
        contact.setFirstname( contactUpdate.getFirstname() );
        contact.setLastname( contactUpdate.getLastname() );
        contact.setAddress( contactUpdate.getAddress() );
        contact.setEmail( contactUpdate.getEmail() );
        contact.setMobilephone( contactUpdate.getMobilephone() );

        return contact;
    }

    @Override
    public ContactUpdate contactToContactUpdate(Contact contact) {
        if ( contact == null ) {
            return null;
        }

        ContactUpdate contactUpdate = new ContactUpdate();

        contactUpdate.setId( contact.getId() );
        contactUpdate.setFirstname( contact.getFirstname() );
        contactUpdate.setLastname( contact.getLastname() );
        contactUpdate.setAddress( contact.getAddress() );
        contactUpdate.setEmail( contact.getEmail() );
        contactUpdate.setMobilephone( contact.getMobilephone() );

        return contactUpdate;
    }
}
