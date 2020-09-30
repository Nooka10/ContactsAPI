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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.NativeWebRequest;

import javax.validation.Valid;
import java.util.List;
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
    public ResponseEntity<List<Contact>> addContact(@Valid ContactAdd contactAdd) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) auth.getPrincipal();
        Optional<User> optionalUser = userRepository.findById(userDetails.getId());
        User user = optionalUser.get();

        Contact contact = contactMapper.contactAddToContact(contactAdd);
        contact.setLinkedUser(user);
        contact = contactRepository.save(contact);

        return ResponseEntity.ok(user.getContacts());
    }

    @Override
    public ResponseEntity<?> deleteContact(long id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) auth.getPrincipal();
        Optional<User> optionalUser = userRepository.findById(userDetails.getId());
        User user = optionalUser.get();

        if (user.removeContactById(id) || user.isAdmin()) {
            contactRepository.deleteById(id);
        } else {
            return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);
        }
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<?> addContactSkill(Long contactId, Long skillId, @Valid SkillLevelAdd skillLevelAdd) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) auth.getPrincipal();
        Optional<User> optionalUser = userRepository.findById(userDetails.getId());
        User user = optionalUser.get();

        Optional<Contact> optionalContact = user.getContactById(contactId);
        if (optionalContact.isEmpty() && !user.isAdmin()) {
            return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);
        } else if (!optionalContact.isEmpty() || user.isAdmin()) {
            Contact contact = optionalContact.get();
            SkillLevel skillLevel = new SkillLevel(skillRepository.findById(skillId).get(), skillLevelAdd.getLevel(), contact);
            skillLevel = skillLevelRepository.save(skillLevel);
            return ResponseEntity.ok(contact);
        } else {
            throw new Error();
        }
    }

    @Override
    public ResponseEntity<?> deleteContactSkill(Long contactId, Long skillId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) auth.getPrincipal();
        Optional<User> optionalUser = userRepository.findById(userDetails.getId());
        User user = optionalUser.get();

        Optional<Contact> optionalContact = user.getContactById(contactId);
        Optional<Skill> optionalSkill = skillRepository.findById(skillId);
        if (optionalContact.isEmpty() && !user.isAdmin()) {
            return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);
        } else if (optionalSkill.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else if ((optionalContact.isPresent() || user.isAdmin()) && optionalSkill.isPresent()) {
            Contact contact = optionalContact.get();
            Skill skill = optionalSkill.get();
            Optional<SkillLevel> optionalSkillLevel = skillLevelRepository.findBySkillAndSkilledContact(skill, contact);
            if (optionalSkillLevel.isPresent()) {
                SkillLevel skillLevel = optionalSkillLevel.get();
                skillLevelRepository.delete(skillLevel);
                return ResponseEntity.ok(contact);
            } else {
                return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);
            }
        } else {
            throw new Error();
        }
    }

    @Override
    public ResponseEntity<?> getContact(long id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) auth.getPrincipal();
        Optional<User> optionalUser = userRepository.findById(userDetails.getId());
        User user = optionalUser.get();

        Optional<Contact> optionalContact = user.getContactById(id);
        if (optionalContact.isEmpty() && !user.isAdmin()) {
            return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);
        } else if (optionalContact.isPresent() || user.isAdmin()) {
            Contact contact = optionalContact.get();
            return ResponseEntity.ok(contact);
        } else {
            throw new Error();
        }
    }

    @Override
    public ResponseEntity<List<Contact>> getContacts(@Valid String name, @Valid String email) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) auth.getPrincipal();
        Optional<User> optionalUser = userRepository.findById(userDetails.getId());
        User user = optionalUser.get();
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

    @Override
    public ResponseEntity<?> updateContact(long id, @Valid ContactUpdate contactUpdate) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) auth.getPrincipal();
        Optional<User> optionalUser = userRepository.findById(userDetails.getId());
        User user = optionalUser.get();

        Optional<Contact> optionalContact = user.getContactById(id);
        if (optionalContact.isEmpty() && !user.isAdmin()) {
            return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);
        } else if (optionalContact.isPresent() || user.isAdmin()) {
            Contact contact = optionalContact.get();
            contact.setFirstname(contactUpdate.getFirstname());
            contact.setLastname(contactUpdate.getLastname());
            contact.setAddress(contactUpdate.getAddress());
            contact.setEmail(contactUpdate.getEmail());
            contact.setMobilephone(contactUpdate.getMobilephone());
            contactRepository.save(contact);
            return ResponseEntity.ok(contact);
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<?> updateContactSkill(Long contactId, Long skillId, @Valid SkillLevelUpdate skillLevelUpdate) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) auth.getPrincipal();
        Optional<User> optionalUser = userRepository.findById(userDetails.getId());
        User user = optionalUser.get();

        Optional<Contact> optionalContact = user.getContactById(contactId);
        Optional<Skill> optionalSkill = skillRepository.findById(skillId);
        if (optionalContact.isEmpty() && !user.isAdmin()) {
            return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);
        } else if (optionalSkill.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else if ((optionalContact.isPresent() || user.isAdmin()) && optionalSkill.isPresent()) {
            Contact contact = optionalContact.get();
            Skill skill = optionalSkill.get();
            Optional<SkillLevel> optionalSkillLevel = skillLevelRepository.findBySkillAndSkilledContact(skill, contact);
            if (optionalSkillLevel.isPresent()) {
                SkillLevel skillLevel = optionalSkillLevel.get();
                skillLevel.setLevel(skillLevelUpdate.getLevel());
                skillLevelRepository.save(skillLevel);
                return ResponseEntity.ok(contact);
            } else {
                return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);
            }
        } else {
            throw new Error();
        }
    }
}
