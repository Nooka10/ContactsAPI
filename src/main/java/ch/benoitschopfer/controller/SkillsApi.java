/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (5.0.0-SNAPSHOT).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package ch.benoitschopfer.controller;

import ch.benoitschopfer.model.other.SkillAddOrUpdate;
import ch.benoitschopfer.model.entity.Skill;
import io.swagger.annotations.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.NativeWebRequest;

import javax.validation.Valid;
import java.util.Optional;

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2020-09-24T16:13:09.139748+02:00[Europe/Paris]")
@Validated
// @Api(value = "skills", description = "the skills API")
public interface SkillsApi {

  default Optional<NativeWebRequest> getRequest() {
    return Optional.empty();
  }

  /**
   * POST /skills : Adds a new skill.
   * Adds a new skill to the system. The connected user must be an admin.
   *
   * @param skillAddOrUpdate Skill to add (required).
   * @return Skill created (status code 201)
   * or Invalid input, received object is invalid (status code 400)
   * or This skill is already in the system (status code 409).
   */
  @ApiOperation(value = "Adds a new skill.", nickname = "addSkill", notes = "Adds a new skill to the system. The connected user must be an admin.", response = Skill.class, authorizations = {
    @Authorization(value = "oauth2", scopes = {
      @AuthorizationScope(scope = "admin", description = "Grants read and write access to anything (his/others contacts and their skills, skills, users).")
    })
  }, tags = {"skills",})
  @ApiResponses(value = {
    @ApiResponse(code = 201, message = "Skill created.", response = Skill.class),
    @ApiResponse(code = 400, message = "Invalid input, received object is invalid."),
    @ApiResponse(code = 409, message = "This skill is already in the system.")})
  @PostMapping(
    value = "/skills",
    produces = {"application/hal+json", "application/json"},
    consumes = {"application/json"}
  )
  @PreAuthorize("hasRole('ADMIN')")
  default ResponseEntity<?> addSkill(@ApiParam(value = "Skill to add.", required = true) @Valid @RequestBody(required = true) SkillAddOrUpdate skillAddOrUpdate) {
    getRequest().ifPresent(request -> {
      for (MediaType mediaType : MediaType.parseMediaTypes(request.getHeader("Accept"))) {
        if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
          String exampleString = "{ \"name\" : \"SpringBoot\", \"id\" : 5, \"usersLevels\" : [ null, null ] }";
          ApiUtil.setExampleResponse(request, "application/json", exampleString);
          break;
        }
      }
    });
    return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

  }


  /**
   * DELETE /skills/{name} : Delete an existing skill.
   * Delete an existing skill. The connected user must be an admin.
   *
   * @param name Name of the skill to delete (required)
   * @return Skill succesfully deleted (status code 204)
   * or Skill not found (status code 404).
   */
  @ApiOperation(value = "Delete an existing skill.", nickname = "deleteSkill", notes = "Delete an existing skill. The connected user must be an admin.", authorizations = {
    @Authorization(value = "oauth2", scopes = {
      @AuthorizationScope(scope = "admin", description = "Grants read and write access to anything (his/others contacts and their skills, skills, users).")
    })
  }, tags = {"skills",})
  @ApiResponses(value = {
    @ApiResponse(code = 204, message = "Skill succesfully deleted."),
    @ApiResponse(code = 404, message = "Skill not found.")})
  @DeleteMapping(
    value = "/skills/{name}"
  )
  @PreAuthorize("hasRole('ADMIN')")
  default ResponseEntity<Void> deleteSkill(@ApiParam(value = "Name of the skill to delete.", required = true) @PathVariable("name") String name) {
    return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

  }


  /**
   * GET /skills/{name} : Get skill by name.
   * Returns the skill corresponding to the received name.
   *
   * @param name Name of the skill to fetch (required).
   * @return The skill corresponding to the received name (status code 200)
   * or Bad input parameter (status code 400).
   */
  @ApiOperation(value = "Get skill by name.", nickname = "getSkill", notes = "Returns the skill corresponding to the received name.", response = Skill.class, responseContainer = "List", authorizations = {
    @Authorization(value = "oauth2", scopes = {
      @AuthorizationScope(scope = "user", description = "Grants read/write access to user resources (his user info, his contacts and their skills)."),
      @AuthorizationScope(scope = "admin", description = "Grants read and write access to anything (his/others contacts and their skills, skills, users).")
    })
  }, tags = {"skills",})
  @ApiResponses(value = {
    @ApiResponse(code = 200, message = "The skill corresponding to the received name.", response = Skill.class),
    @ApiResponse(code = 400, message = "Bad input parameter.")})
  @GetMapping(
    value = "/skills/{name}",
    produces = {"application/hal+json", "application/json"}
  )
  @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
  default ResponseEntity<Skill> getSkill(@ApiParam(value = "Name of the skill to fetch.", required = true) @PathVariable("name") String name) {
    getRequest().ifPresent(request -> {
      for (MediaType mediaType : MediaType.parseMediaTypes(request.getHeader("Accept"))) {
        if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
          String exampleString = "{ \"name\" : \"SpringBoot\", \"id\" : 5, \"usersLevels\" : [ null, null ] }";
          ApiUtil.setExampleResponse(request, "application/json", exampleString);
          break;
        }
      }
    });
    return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

  }


  /**
   * GET /skills : Get all skills.
   * Returns all skills whose name contains the received string.
   *
   * @param name Returns all skills whose name contains the received string (optional).
   * @return Search results matching criteria (status code 200)
   * or bad input parameter (status code 400).
   */
  @ApiOperation(value = "Get all skills", nickname = "getSkills", notes = "Returns all skills whose name contains the received string.", response = Skill.class, responseContainer = "List", authorizations = {
    @Authorization(value = "oauth2", scopes = {
      @AuthorizationScope(scope = "user", description = "Grants read/write access to user resources (his user info, his contacts and their skills)."),
      @AuthorizationScope(scope = "admin", description = "Grants read and write access to anything (his/others contacts and their skills, skills, users).")
    })
  }, tags = {"skills",})
  @ApiResponses(value = {
    @ApiResponse(code = 200, message = "Returns all skills whose name contains the received string.", response = Skill.class, responseContainer = "List"),
    @ApiResponse(code = 400, message = "Bad input parameter.")})
  @GetMapping(
    value = "/skills",
    produces = {"application/hal+json", "application/json"}
  )
  @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
  default ResponseEntity<Page<Skill>> getSkills(@ApiParam(value = "Returns all skills whose name contains the received string.") @Valid @RequestParam(value = "name", required = false) String name, Pageable pageable) {
    getRequest().ifPresent(request -> {
      for (MediaType mediaType : MediaType.parseMediaTypes(request.getHeader("Accept"))) {
        if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
          String exampleString = "{ \"name\" : \"SpringBoot\", \"id\" : 5, \"usersLevels\" : [ null, null ] }";
          ApiUtil.setExampleResponse(request, "application/json", exampleString);
          break;
        }
      }
    });
    return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
  }


  /**
   * PUT /skills/{name} : Update an existing skill.
   * Update an existing skill. The connected user must be an admin.
   *
   * @param name          Name of the skill to fetch (required).
   * @param skillAddOrUpdate Skill's info to update (required).
   * @return skill succesfully updated (status code 200)
   * or Invalid skill supplied (status code 400)
   * or Skill not found (status code 404).
   */
  @ApiOperation(value = "Update an existing skill.", nickname = "updateSkill", notes = "Update an existing skill. The connected user must be an admin.", response = Skill.class, authorizations = {
    @Authorization(value = "oauth2", scopes = {
      @AuthorizationScope(scope = "admin", description = "Grants read and write access to anything (his/others contacts and their skills, skills, users).")
    })
  }, tags = {"skills",})
  @ApiResponses(value = {
    @ApiResponse(code = 200, message = "Skill succesfully updated.", response = Skill.class),
    @ApiResponse(code = 400, message = "Invalid skill supplied."),
    @ApiResponse(code = 404, message = "Skill not found.")})
  @PutMapping(
    value = "/skills/{name}",
    produces = {"application/hal+json", "application/json"},
    consumes = {"application/json"}
  )
  @PreAuthorize("hasRole('ADMIN')")
  default ResponseEntity<Skill> updateSkill(@ApiParam(value = "Name of the skill to update.", required = true) @PathVariable("name") String name,
                                                  @ApiParam(value = "Skill's info to update.", required = true) @Valid @RequestBody(required = true) SkillAddOrUpdate skillAddOrUpdate) {
    getRequest().ifPresent(request -> {
      for (MediaType mediaType : MediaType.parseMediaTypes(request.getHeader("Accept"))) {
        if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
          String exampleString = "{ \"name\" : \"SpringBoot\", \"id\" : 5, \"usersLevels\" : [ null, null ] }";
          ApiUtil.setExampleResponse(request, "application/json", exampleString);
          break;
        }
      }
    });
    return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

  }
}
