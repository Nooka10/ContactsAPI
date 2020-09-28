package ch.benoitschopfer.controller;

import ch.benoitschopfer.model.DTO.UserAddOrUpdate;
import ch.benoitschopfer.model.User;
import ch.benoitschopfer.model.mappers.UserMapper;
import ch.benoitschopfer.repository.UserRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.NativeWebRequest;

import javax.validation.Valid;
import java.util.Optional;

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2020-09-24T16:13:09.139748+02:00[Europe/Paris]")
@Controller
@RequestMapping("${openapi.Contacts API.base-path:/api}")
public class UsersApiController implements UsersApi {

  private final NativeWebRequest request;

  private UserMapper userMapper = Mappers.getMapper(UserMapper.class);

  @Autowired
  UserRepository userRepository;

  @Autowired
  public UsersApiController(NativeWebRequest request) {
    this.request = request;
  }

  @Override
  public Optional<NativeWebRequest> getRequest() {
    return Optional.ofNullable(request);
  }

  @Override
  public ResponseEntity<Void> deleteUser(long id) {
    Optional<User> user = userRepository.findById(id);

    if (user.isEmpty()) {
      return ResponseEntity.notFound().build();
    } else {
      userRepository.deleteById(id);
      return ResponseEntity.noContent().build();
    }
  }

  @Override
  public ResponseEntity<User> getUser(long id) {
    Optional<User> user = userRepository.findById(id);

    if (user.isEmpty()) { return ResponseEntity.notFound().build(); }

    return ResponseEntity.ok(user.get());
  }

  @Override
  public ResponseEntity<Page<User>> getUsers(@Valid String email, Pageable pageable) {
    if (email != null) {
      Specification<User> spec = (user, cq, cb) -> cb.like(user.get("email"), "%" + email + "%");
      return ResponseEntity.ok(userRepository.findAll(spec, pageable));
    } else {
      return ResponseEntity.ok(userRepository.findAll(pageable));
    }
  }

  @Override
  public ResponseEntity<User> updateUser(long id, UserAddOrUpdate userAddOrUpdate) {
    // userAddOrUpdate param is willingly not validated with @Valid
    Optional<User> optionalUser = userRepository.findById(id);

    if (optionalUser.isEmpty()) {
      return ResponseEntity.notFound().build();
    }

    User user = optionalUser.get();

    String email = userAddOrUpdate.getEmail();
    String password = userAddOrUpdate.getPassword();

    if (email != null) {
      user.setEmail(email);
    }
    if (password != null) {
      user.setPassword(password);
    }

    userRepository.save(user);

    return ResponseEntity.ok(user);
  }
}
