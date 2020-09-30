package ch.benoitschopfer.controller;

import ch.benoitschopfer.model.entity.Contact;
import ch.benoitschopfer.model.entity.Skill;
import ch.benoitschopfer.model.entity.SkillLevel;
import ch.benoitschopfer.model.entity.User;
import ch.benoitschopfer.model.mappers.ContactMapper;
import ch.benoitschopfer.model.mappers.SkillLevelMapper;
import ch.benoitschopfer.model.other.ContactAdd;
import ch.benoitschopfer.model.other.ContactUpdate;
import ch.benoitschopfer.model.other.SkillLevelAdd;
import ch.benoitschopfer.model.other.SkillLevelUpdate;
import ch.benoitschopfer.repository.ContactRepository;
import ch.benoitschopfer.repository.SkillLevelRepository;
import ch.benoitschopfer.repository.SkillRepository;
import ch.benoitschopfer.repository.UserRepository;
import ch.benoitschopfer.service.UserDetailsImpl;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2020-09-24T16:13:09.139748+02:00[Europe/Paris]")
@RestController
@RequestMapping("${openapi.Contacts API.base-path:/api/secured}")
public class ContactsApiController implements ContactsApi {

  private final NativeWebRequest request;

  @Autowired
  UserRepository userRepository;

  @Autowired
  ContactRepository contactRepository;

  @Autowired
  SkillRepository skillRepository;

  @Autowired
  SkillLevelRepository skillLevelRepository;

  private ContactMapper contactMapper = Mappers.getMapper(ContactMapper.class);

  private SkillLevelMapper skillLevelMapper = Mappers.getMapper(SkillLevelMapper.class);

  @Autowired
  public ContactsApiController(NativeWebRequest request) {
    this.request = request;
  }

  @Override
  public Optional<NativeWebRequest> getRequest() {
    return Optional.ofNullable(request);
  }

  @Override
  public ResponseEntity<?> addContact(@Valid ContactAdd contactAdd) {
    try {
      Authentication auth = SecurityContextHolder.getContext().getAuthentication();
      UserDetailsImpl userDetails = (UserDetailsImpl) auth.getPrincipal();
      Optional<User> optionalUser = userRepository.findById(userDetails.getId());
      User user = optionalUser.get();

      Contact contact = contactMapper.contactAddToContact(contactAdd);
      contact.setLinkedUser(user);
      contact = contactRepository.save(contact);

      String location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/contacts/{id}").buildAndExpand(contact.getId()).toUriString();
      EntityModel<Contact> contactResource = new EntityModel<>(contact, Link.of(location, "self"));

      return ResponseEntity
        .created(new URI(location))
        .body(contactResource);
    } catch (URISyntaxException e) {
      return ResponseEntity.badRequest().body("Unable to create a new contact");
    }
  }

  @Override
  public ResponseEntity<?> deleteContact(long id) {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    UserDetailsImpl userDetails = (UserDetailsImpl) auth.getPrincipal();
    Optional<User> optionalUser = userRepository.findById(userDetails.getId());
    User user = optionalUser.get();

    if (user.isAdmin()) { // an admin can delete any contact in the system
      contactRepository.deleteById(id);
    } else if (user.removeContactById(id)) { // a user can delete only his contacts
      contactRepository.deleteById(id);
    } else {
      return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);
    }
    return ResponseEntity.noContent().build();
  }

  @Override
  public ResponseEntity<?> getContact(long id) {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    UserDetailsImpl userDetails = (UserDetailsImpl) auth.getPrincipal();
    Optional<User> optionalUser = userRepository.findById(userDetails.getId());
    User user = optionalUser.get();

    try {
      if (user.isAdmin()) { // user is an admin -> can get any contact
        return ResponseEntity.ok(contactRepository.findById(id).get());
      } else { // user is not an admin -> can get only his contacts
        Contact contact = user.getContactById(id).get();
        return ResponseEntity.ok(contact);
      }
    } catch (NoSuchElementException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("You have no contact with the id " + id + "!");
    }
  }

  @Override
  public ResponseEntity<List<Contact>> getContacts(@Valid String name, @Valid String email) {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    UserDetailsImpl userDetails = (UserDetailsImpl) auth.getPrincipal();
    Optional<User> optionalUser = userRepository.findById(userDetails.getId());
    User user = optionalUser.get();

    if (user.isAdmin()) { // user is an admin -> can get all contacts in the system
      if (name == null && email == null) {
        return ResponseEntity.ok(contactRepository.findAll());
      } else {
        Specification<Contact> spec = (contact, cq, cb) -> {
          if (name != null && email != null) {
            return cb.and(
              cb.like(contact.get("fullname"), "%" + name + "%"),
              cb.like(contact.get("email"), "%" + email + "%")
            );
          }
          if (name != null) {
            return cb.like(contact.get("fullname"), "%" + name + "%");
          }
          return cb.like(contact.get("email"), "%" + email + "%");
        };
        return ResponseEntity.ok(contactRepository.findAll(spec));
      }
    } else { // user is not an admin -> can get only his contacts
      Specification<Contact> spec = (contact, cq, cb) -> {
        if (name == null && email == null) {
          return cb.equal(contact.get("linkedUser").get("id"), user.getId());
        }
        if (name != null && email != null) {
          return cb.and(
            cb.equal(contact.get("linkedUser").get("id"), user.getId()),
            cb.and(
              cb.like(contact.get("fullname"), "%" + name + "%"),
              cb.like(contact.get("email"), "%" + email + "%")
            )
          );
        }
        if (name != null) {
          return cb.and(
            cb.equal(contact.get("linkedUser").get("id"), user.getId()),
            cb.like(contact.get("fullname"), "%" + name + "%")
          );
        }
        return cb.and(
          cb.equal(contact.get("linkedUser").get("id"), user.getId()),
          cb.like(contact.get("email"), "%" + email + "%")
        );
      };
      return ResponseEntity.ok(contactRepository.findAll(spec));
    }
  }

  /**
   * Update the contactToUpdate with the info contained in contactInfoUpdated
   *
   * @param contactToUpdate    the contact to update.
   * @param contactInfoUpdated the updated information to save.
   * @return the contact updated and saved in repository.
   */
  private Contact updateContact(Contact contactToUpdate, ContactUpdate contactInfoUpdated) {
    contactToUpdate.setFirstname(contactInfoUpdated.getFirstname());
    contactToUpdate.setLastname(contactInfoUpdated.getLastname());
    contactToUpdate.setAddress(contactInfoUpdated.getAddress());
    contactToUpdate.setEmail(contactInfoUpdated.getEmail());
    contactToUpdate.setMobilephone(contactInfoUpdated.getMobilephone());
    return contactRepository.save(contactToUpdate);
  }

  @Override
  public ResponseEntity<?> updateContact(long id, @Valid ContactUpdate contactUpdate) {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    UserDetailsImpl userDetails = (UserDetailsImpl) auth.getPrincipal();
    Optional<User> optionalUser = userRepository.findById(userDetails.getId());
    User user = optionalUser.get();

    if (user.isAdmin()) { // an admin can update any contact in the system
      try {
        Contact contact = contactRepository.findById(id).get();
        return ResponseEntity.ok(updateContact(contact, contactUpdate));
      } catch (NoSuchElementException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("There is no contact with the id " + id + "!");
      }
    } else { // a user can update only his contacts
      try {
        Contact contact = user.getContactById(id).get();
        return ResponseEntity.ok(updateContact(contact, contactUpdate));
      } catch (NoSuchElementException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("You have no contact with the id " + id + "!");
      }
    }
  }

  @Override
  public ResponseEntity<?> addContactSkill(Long contactId, Long skillId, @Valid SkillLevelAdd skillLevelAdd) {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    UserDetailsImpl userDetails = (UserDetailsImpl) auth.getPrincipal();
    Optional<User> optionalUser = userRepository.findById(userDetails.getId());
    User user = optionalUser.get();

    try {
      if (user.isAdmin()) { // an admin can add skills to any contact in the system
        try {
          Contact contact = contactRepository.findById(contactId).get();
          SkillLevel skillLevel = new SkillLevel(skillRepository.findById(skillId).get(), skillLevelAdd.getLevel(), contact);
          skillLevel = skillLevelRepository.save(skillLevel);

          String location = ServletUriComponentsBuilder.fromCurrentContextPath()
            .path("/contacts/{contactId}/skills/{skillId}")
            .buildAndExpand(contactId, skillId)
            .toUriString();
          EntityModel<SkillLevel> skillResource = new EntityModel<>(skillLevel, Link.of(location, "self"));

          return ResponseEntity
            .created(new URI(location))
            .body(skillResource);
        } catch (NoSuchElementException e) {
          return ResponseEntity.status(HttpStatus.NOT_FOUND).body("There is no contact with the id " + contactId + "!");
        }
      } else { // an user can only add skills to his contacts
        try {
          Contact contact = user.getContactById(contactId).get();
          SkillLevel skillLevel = new SkillLevel(skillRepository.findById(skillId).get(), skillLevelAdd.getLevel(), contact);
          skillLevel = skillLevelRepository.save(skillLevel);

          String location = ServletUriComponentsBuilder.fromCurrentContextPath()
            .path("/contacts/{contactId}/skills/{skillId}")
            .buildAndExpand(contactId, skillId)
            .toUriString();
          EntityModel<SkillLevel> skillResource = new EntityModel<>(skillLevel, Link.of(location, "self"));

          return ResponseEntity
            .created(new URI(location))
            .body(skillResource);
        } catch (NoSuchElementException e) {
          return ResponseEntity.status(HttpStatus.NOT_FOUND).body("You have no contact with the id " + contactId + "!");
        }
      }
    } catch (URISyntaxException e) {
      return ResponseEntity.badRequest().body("Unable to create a new contact");
    } catch (NoSuchElementException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("There is no skill with the id " + skillId + "!");
    }
  }

  @Override
  public ResponseEntity<?> deleteContactSkill(Long contactId, Long skillId) {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    UserDetailsImpl userDetails = (UserDetailsImpl) auth.getPrincipal();
    Optional<User> optionalUser = userRepository.findById(userDetails.getId());
    User user = optionalUser.get();

    try {
      Skill skill = skillRepository.findById(skillId).get();
      if (user.isAdmin()) { // an admin can delete skills to any contact in the system
        try {
          Contact contact = contactRepository.findById(contactId).get();
          skillLevelRepository.deleteBySkillAndSkilledContact(skill, contact);
        } catch (NoSuchElementException e) {
          return ResponseEntity.status(HttpStatus.NOT_FOUND).body("There is no contact with the id " + contactId + "!");
        }
      } else { // an user can only delete skills to his contacts
        try {
          Contact contact = user.getContactById(contactId).get();
          skillLevelRepository.deleteBySkillAndSkilledContact(skill, contact);
        } catch (NoSuchElementException e) {
          return ResponseEntity.status(HttpStatus.NOT_FOUND).body("You have no contact with the id " + contactId + "!");
        }
      }
      return ResponseEntity.noContent().build();
    } catch (NoSuchElementException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("There is no skill with the id " + skillId + "!");
    }
  }

  @Override
  public ResponseEntity<?> updateContactSkill(Long contactId, Long skillId, @Valid SkillLevelUpdate skillLevelUpdate) {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    UserDetailsImpl userDetails = (UserDetailsImpl) auth.getPrincipal();
    Optional<User> optionalUser = userRepository.findById(userDetails.getId());
    User user = optionalUser.get();

    try {
      Skill skill = skillRepository.findById(skillId).get();
      if (user.isAdmin()) { // an admin can update skills to any contact in the system
        try {
          Contact contact = contactRepository.findById(contactId).get();
          Optional<SkillLevel> optionalSkillLevel = skillLevelRepository.findBySkillAndSkilledContact(skill, contact);
          if (optionalSkillLevel.isPresent()) {
            SkillLevel skillLevel = optionalSkillLevel.get();
            skillLevel.setLevel(skillLevelUpdate.getLevel());
            skillLevelRepository.save(skillLevel);
            return ResponseEntity.ok(skillLevel);
          } else {
            return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);
          }
        } catch (NoSuchElementException e) {
          return ResponseEntity.status(HttpStatus.NOT_FOUND).body("There is no contact with the id " + contactId + "!");
        }
      } else { // an user can only update skills to his contacts
        try {
          Contact contact = user.getContactById(contactId).get();
          Optional<SkillLevel> optionalSkillLevel = skillLevelRepository.findBySkillAndSkilledContact(skill, contact);
          if (optionalSkillLevel.isPresent()) {
            SkillLevel skillLevel = optionalSkillLevel.get();
            skillLevel.setLevel(skillLevelUpdate.getLevel());
            skillLevelRepository.save(skillLevel);
            return ResponseEntity.ok(skillLevel);
          } else {
            return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);
          }
        } catch (NoSuchElementException e) {
          return ResponseEntity.status(HttpStatus.NOT_FOUND).body("You have no contact with the id " + contactId + "!");
        }
      }
    } catch (NoSuchElementException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("There is no skill with the id " + skillId + "!");
    }
  }
}
