package ch.benoitschopfer.controller;

import ch.benoitschopfer.model.entity.Contact;
import ch.benoitschopfer.model.entity.User;
import ch.benoitschopfer.model.mappers.ContactMapper;
import ch.benoitschopfer.model.other.ContactAdd;
import ch.benoitschopfer.model.other.ContactUpdate;
import ch.benoitschopfer.model.other.SkillLevelAdd;
import ch.benoitschopfer.model.other.SkillLevelUpdate;
import ch.benoitschopfer.repository.UserRepository;
import ch.benoitschopfer.service.UserDetailsImpl;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
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

    private ContactMapper contactMapper = Mappers.getMapper(ContactMapper.class);

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


        user.addContact(contactMapper.contactAddToContact(contactAdd));
        user = userRepository.save(user);
        return ResponseEntity.ok(user.getContacts());
    }

    @Override
    public ResponseEntity<List<Contact>> addContactSkill(Long contactId, Long skillId, @Valid SkillLevelAdd skillLevelAdd) {
        return null;
    }

    @Override
    public ResponseEntity<Void> deleteContact(long id) {
        return null;
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
