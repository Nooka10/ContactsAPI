package ch.benoitschopfer.controller;

import ch.benoitschopfer.model.entity.Role;
import ch.benoitschopfer.model.mappers.RoleMapper;
import ch.benoitschopfer.repository.RoleRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2020-09-24T16:13:09.139748+02:00[Europe/Paris]")
@RestController
@RequestMapping("${openapi.Contacts API.base-path:/api/secured}")
public class RolesApiController implements RolesApi {

  private final NativeWebRequest request;

  private RoleMapper roleMapper = Mappers.getMapper(RoleMapper.class);

  @Autowired
  RoleRepository roleRepository;

  @Autowired
  public RolesApiController(NativeWebRequest request) {
    this.request = request;
  }

  @Override
  public Optional<NativeWebRequest> getRequest() {
    return Optional.ofNullable(request);
  }

  @Override
  public ResponseEntity<?> addRoles(@Valid String roleName) {
    try {
      Role savedRole = roleRepository.save(roleMapper.stringToRole(roleName));

      String location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/roles/{id}").buildAndExpand(savedRole.getId()).toUriString();
      EntityModel<Role> RoleResource = new EntityModel<>(savedRole, Link.of(location, "self"));

      return ResponseEntity
        .created(new URI(location))
        .body(RoleResource);
    } catch (URISyntaxException e) {
      return ResponseEntity.badRequest().body("Unable to create " + roleName);
    }
  }

  @Override
  public ResponseEntity<Page<Role>> getRoles(Pageable pageable) {
    Page<Role> roles = roleRepository.findAll(pageable);
    return ResponseEntity.ok(roles);
  }
}
