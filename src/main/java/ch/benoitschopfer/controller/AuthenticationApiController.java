package ch.benoitschopfer.controller;

import ch.benoitschopfer.configuration.JWT.JwtTokenUtil;
import ch.benoitschopfer.error.MessageResponse;
import ch.benoitschopfer.model.entity.Role;
import ch.benoitschopfer.model.entity.User;
import ch.benoitschopfer.model.mappers.UserMapper;
import ch.benoitschopfer.model.other.JwtResponse;
import ch.benoitschopfer.model.other.LoginRequest;
import ch.benoitschopfer.model.other.RegisterRequest;
import ch.benoitschopfer.repository.RoleRepository;
import ch.benoitschopfer.repository.UserRepository;
import ch.benoitschopfer.service.UserDetailsImpl;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2020-09-24T16:13:09.139748+02:00[Europe/Paris]")
@RestController
@RequestMapping("${openapi.Contacts API.base-path:/api/auth}")
public class AuthenticationApiController implements AuthenticationApi {

  private final NativeWebRequest request;

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private JwtTokenUtil jwtTokenUtil;

  @Autowired
  private UserDetailsService userDetailsService;

  @Autowired
  private RoleRepository roleRepository;

  private UserMapper userMapper = Mappers.getMapper(UserMapper.class);

  @Autowired
  UserRepository userRepository;

  @Autowired
  public AuthenticationApiController(NativeWebRequest request) {
    this.request = request;
  }

  @Override
  public Optional<NativeWebRequest> getRequest() {
    return Optional.ofNullable(request);
  }

  @Override
  public ResponseEntity<?> login(@Valid LoginRequest loginRequest) throws Exception {
    Authentication authentication;
    try {
      authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
    } catch (DisabledException e) {
      throw new Exception("USER_DISABLED", e);
    } catch (BadCredentialsException e) {
      throw new Exception("INVALID_CREDENTIALS", e);
    }

    SecurityContextHolder.getContext().setAuthentication(authentication);
    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

    String jwt = jwtTokenUtil.generateToken(userDetails);

    List<String> roles = userDetails.getAuthorities()
      .stream()
      .map(item -> item.getAuthority())
      .collect(Collectors.toList());

    return ResponseEntity.ok(new JwtResponse(jwt,
      userDetails.getId(),
      userDetails.getEmail(),
      roles));
  }

  @Override
  public ResponseEntity<?> logout() {
    SecurityContextHolder.getContext().setAuthentication(null);
    return ResponseEntity.noContent().build();
  }

  @Override
  public ResponseEntity<?> register(@Valid RegisterRequest registerRequest) throws Exception {
    try {
      if (userRepository.existsByEmail(registerRequest.getEmail())) {
        return ResponseEntity
          .badRequest()
          .body(new MessageResponse("Error: This email is already used!"));
      }

      User user = userMapper.registerRequestToUser(registerRequest);
      for (String role : registerRequest.getRole()) {
        Optional<Role> r = roleRepository.findByName(role);
        user.addRole(r.get());
      }
      User savedUser = userRepository.save(user);

      String location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/users/{id}").buildAndExpand(savedUser.getId()).toUriString();
      EntityModel<User> UserResource = new EntityModel<>(savedUser, Link.of(location, "self"));

      login(new LoginRequest(registerRequest.getEmail(), registerRequest.getPassword()));

      return ResponseEntity
        .created(new URI(location))
        .body(UserResource);
    } catch (URISyntaxException e) {
      return ResponseEntity.badRequest().body("Unable to create " + registerRequest);
    }
  }
}
