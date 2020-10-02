/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (5.0.0-SNAPSHOT).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package ch.benoitschopfer.controller;

import ch.benoitschopfer.model.entity.Role;
import io.swagger.annotations.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.context.request.NativeWebRequest;

import javax.validation.Valid;
import java.util.Optional;

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2020-09-24T16:13:09.139748+02:00[Europe/Paris]")
@Validated
@Api(value = "roles", tags = "roles")
public interface RolesApi {

  default Optional<NativeWebRequest> getRequest() {
    return Optional.empty();
  }

  /**
   * POST /roles : Adds a new role.
   * Adds a new role in the system.
   *
   * @param role Name of the role to add (required).
   * @return Role created (status code 201)
   * or Invalid input, received object is invalid (status code 400)
   * or This role already exist (status code 409).
   */
  @ApiOperation(
    value = "Adds a new role.",
    nickname = "addRole",
    notes = "Adds a new role in the system.",
    response = Role.class,
    responseContainer = "List",
    authorizations = {
      @Authorization(value = "bearer", scopes = {
        @AuthorizationScope(scope = "admin", description = "Grants read and write access to anything (his/others contacts and their skills, skills, users).")
      })
    },
    tags = {"roles"}
  )
  @ApiResponses(value = {
    @ApiResponse(code = 201, message = "Role created.", response = Role.class, responseContainer = "List"),
    @ApiResponse(code = 400, message = "Invalid input, received object is invalid."),
    @ApiResponse(code = 409, message = "This role already exist.")
  })
  @PostMapping(
    value = "/roles",
    produces = {"application/json"},
    consumes = {"application/json"}
  )
  @PreAuthorize("hasRole('ADMIN')")
  default ResponseEntity<?> addRoles(@ApiParam(value = "Name of the role to add.", required = true) @Valid @RequestBody(required = true) String roleName) {
    return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
  }

  /**
   * GET /roles : Get all roles.
   * Returns all roles.
   *
   * @return Returns all roles (status code 200)
   * or bad input parameter (status code 400).
   */
  @ApiOperation(
    value = "Get all roles.",
    nickname = "getRoles",
    notes = "Returns all roles whose name contains the received string.",
    response = Role.class,
    responseContainer = "List",
    authorizations = {
      @Authorization(value = "bearer", scopes = {
        @AuthorizationScope(scope = "admin", description = "Grants read and write access to anything (his/others contacts and their skills, skills, users).")
      })
    },
    tags = {"roles"}
  )
  @ApiResponses(value = {
    @ApiResponse(code = 200, message = "Returns all roles whose name contains the received string.", response = Role.class, responseContainer = "List"),
    @ApiResponse(code = 400, message = "Bad input parameter.")})
  @GetMapping(
    value = "/roles",
    produces = {"application/json"}
  )
  @PreAuthorize("hasRole('ADMIN')")
  default ResponseEntity<Page<Role>> getRoles(Pageable pageable) {
    return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
  }
}
