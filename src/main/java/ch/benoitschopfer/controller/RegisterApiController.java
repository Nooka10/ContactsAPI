package ch.benoitschopfer.controller;

import ch.benoitschopfer.model.DTO.UserToAddOrUpdate;
import ch.benoitschopfer.model.User;
import ch.benoitschopfer.model.mappers.UserMapper;
import ch.benoitschopfer.repository.UserRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2020-09-24T16:13:09.139748+02:00[Europe/Paris]")
@RestController
@RequestMapping("${openapi.Contacts API.base-path:/api}")
public class RegisterApiController implements RegisterApi {

  private final NativeWebRequest request;

  private UserMapper userMapper = Mappers.getMapper(UserMapper.class);

  @Autowired
  UserRepository userRepository;

  @org.springframework.beans.factory.annotation.Autowired
  public RegisterApiController(NativeWebRequest request) {
    this.request = request;
  }

  @Override
  public Optional<NativeWebRequest> getRequest() {
    return Optional.ofNullable(request);
  }

  @Override
  public ResponseEntity<?> register(@Valid UserToAddOrUpdate userToAddOrUpdate) {
    try {
      User savedUser = userRepository.save(userMapper.userToAddOrUpdateToUser(userToAddOrUpdate));

      String location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/users/{id}").buildAndExpand(savedUser.getId()).toUriString();
      EntityModel<User> UserResource = new EntityModel<>(savedUser, new Link(location, "self"));

      return ResponseEntity
        .created(new URI(location))
        .body(UserResource);
    } catch (URISyntaxException e) {
      return ResponseEntity.badRequest().body("Unable to create " + userToAddOrUpdate);
    }
  }
}
