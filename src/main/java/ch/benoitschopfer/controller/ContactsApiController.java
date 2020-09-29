package ch.benoitschopfer.controller;

import ch.benoitschopfer.model.entity.Contact;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
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
    @Transactional
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
    @Transactional
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
    public ResponseEntity<Void> deleteContactSkill(Long contactId, Long skillId) {
        return null;
    }

    @Override
    public ResponseEntity<List<Contact>> getContact(long id) {
        return null;
    }

    @Override
    public ResponseEntity<List<Contact>> getContacts(@Valid String name, @Valid String email) {
        return null;
    }

    @Override
    public ResponseEntity<List<Contact>> updateContact(long id, @Valid ContactUpdate contactUpdate) {
        return null;
    }

    @Override
    public ResponseEntity<List<Contact>> updateContactSkill(Long contactId, Long skillId, @Valid SkillLevelUpdate skillLevelUpdate) {
        return null;
    }
}
