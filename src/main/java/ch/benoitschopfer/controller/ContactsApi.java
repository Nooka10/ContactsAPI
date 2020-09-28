/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (5.0.0-SNAPSHOT).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package ch.benoitschopfer.controller;

import ch.benoitschopfer.model.Contact;
import ch.benoitschopfer.model.*;
import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.NativeWebRequest;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2020-09-24T16:13:09.139748+02:00[Europe/Paris]")
@Validated
@Api(value = "contacts", description = "the contacts API")
public interface ContactsApi {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

  /**
   * POST /contacts : Adds a new contact
   * Adds a new contact for the connected user
   *
   * @param contactToAdd Contact to add (optional)
   * @return Contact created (status code 201)
   * or Invalid input, received object is invalid (status code 400)
   * or This email adress is already used (status code 409)
   */
    @ApiOperation(value = "Adds a new contact", nickname = "addContact", notes = "Adds a new contact for the connected user", response = Contact.class, responseContainer = "List", authorizations = {
      @Authorization(value = "oauth2", scopes = {
        @AuthorizationScope(scope = "user", description = "Grants read/write access to user resources (his user info, his contacts and their skills)"),
        @AuthorizationScope(scope = "admin", description = "Grants read and write access to anything (his/others contacts and their skills, skills, users)")
      })
    }, tags = {"contacts",})
    @ApiResponses(value = {
      @ApiResponse(code = 201, message = "Contact created", response = Contact.class, responseContainer = "List"),
      @ApiResponse(code = 400, message = "Invalid input, received object is invalid"),
      @ApiResponse(code = 409, message = "This email adress is already used")})
    @PostMapping(
      value = "/contacts",
      produces = {"application/json"},
      consumes = {"application/json"}
    )
    default ResponseEntity<List<Contact>> addContact(@ApiParam(value = "Contact to add") @Valid @RequestBody(required = false) ContactToAdd contactToAdd) {
      getRequest().ifPresent(request -> {
        for (MediaType mediaType : MediaType.parseMediaTypes(request.getHeader("Accept"))) {
          if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
            String exampleString = "{ \"skills\" : [ { \"level\" : 5.637376656633329, \"skill\" : { \"name\" : \"SpringBoot\", \"id\" : 5, \"usersLevels\" : [ null, null ] }, \"id\" : 1 }, { \"level\" : 5.637376656633329, \"skill\" : { \"name\" : \"SpringBoot\", \"id\" : 5, \"usersLevels\" : [ null, null ] }, \"id\" : 1 } ], \"firstname\" : \"firstname\", \"address\" : \"address\", \"mobilephone\" : \"mobilephone\", \"id\" : 6, \"fullname\" : \"fullname\", \"email\" : \"email\", \"lastname\" : \"lastname\" }";
            ApiUtil.setExampleResponse(request, "application/json", exampleString);
            break;
          }
        }
      });
      return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


  /**
   * POST /contacts/{contactId}/skills/{skillId} : Adds a new skill level to a contact
   * Adds a new skill to a contact of the connected user.
   *
   * @param contactId       Id of the contact to fetch (required)
   * @param skillId         Id of the skill to modify or delete (required)
   * @param skillLevelToAdd SkillLevel to add (optional)
   * @return Contact&#39;s skill level succesfully created (status code 201)
   * or Invalid input, received object is invalid (status code 400)
   * or This skill is already in the system (status code 409)
   */
  @ApiOperation(value = "Adds a new skill level to a contact", nickname = "addContactSkill", notes = "Adds a new skill to a contact of the connected user.", response = Contact.class, responseContainer = "List", authorizations = {
    @Authorization(value = "oauth2", scopes = {
      @AuthorizationScope(scope = "user", description = "Grants read/write access to user resources (his user info, his contacts and their skills)"),
      @AuthorizationScope(scope = "admin", description = "Grants read and write access to anything (his/others contacts and their skills, skills, users)")
    })
  }, tags = {"contactSkills",})
  @ApiResponses(value = {
    @ApiResponse(code = 201, message = "Contact's skill level succesfully created", response = Contact.class, responseContainer = "List"),
    @ApiResponse(code = 400, message = "Invalid input, received object is invalid"),
    @ApiResponse(code = 409, message = "This skill is already in the system")})
  @PostMapping(
    value = "/contacts/{contactId}/skills/{skillId}",
    produces = {"application/json"},
    consumes = {"application/json"}
  )
  default ResponseEntity<List<Contact>> addContactSkill(@ApiParam(value = "Id of the contact to fetch", required = true) @PathVariable("contactId") Long contactId,
                                                        @ApiParam(value = "Id of the skill to modify or delete", required = true) @PathVariable("skillId") Long skillId,
                                                        @ApiParam(value = "SkillLevel to add") @Valid @RequestBody(required = false) SkillLevelToAdd skillLevelToAdd) {
    getRequest().ifPresent(request -> {
      for (MediaType mediaType : MediaType.parseMediaTypes(request.getHeader("Accept"))) {
        if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
          String exampleString = "{ \"skills\" : [ { \"level\" : 5.637376656633329, \"skill\" : { \"name\" : \"SpringBoot\", \"id\" : 5, \"usersLevels\" : [ null, null ] }, \"id\" : 1 }, { \"level\" : 5.637376656633329, \"skill\" : { \"name\" : \"SpringBoot\", \"id\" : 5, \"usersLevels\" : [ null, null ] }, \"id\" : 1 } ], \"firstname\" : \"firstname\", \"address\" : \"address\", \"mobilephone\" : \"mobilephone\", \"id\" : 6, \"fullname\" : \"fullname\", \"email\" : \"email\", \"lastname\" : \"lastname\" }";
          ApiUtil.setExampleResponse(request, "application/json", exampleString);
          break;
        }
      }
    });
    return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

  }


  /**
   * DELETE /contacts/{id} : Delete an existing contact
     * Delete an existing contact of the connected user
     *
     * @param id Id of the contact to fetch (required)
     * @return contact succesfully deleted (status code 200)
   *         or Contact not found (status code 404)
   */
  @ApiOperation(value = "Delete an existing contact", nickname = "deleteContact", notes = "Delete an existing contact of the connected user", authorizations = {
    @Authorization(value = "oauth2", scopes = {
      @AuthorizationScope(scope = "user", description = "Grants read/write access to user resources (his user info, his contacts and their skills)"),
      @AuthorizationScope(scope = "admin", description = "Grants read and write access to anything (his/others contacts and their skills, skills, users)")
    })
  }, tags = {"contacts",})
  @ApiResponses(value = {
    @ApiResponse(code = 200, message = "contact succesfully deleted"),
    @ApiResponse(code = 404, message = "Contact not found")})
  @DeleteMapping(
    value = "/contacts/{id}"
  )
  default ResponseEntity<Void> deleteContact(@ApiParam(value = "Id of the contact to fetch", required = true) @PathVariable("id") Long id) {
    return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

  }


  /**
   * DELETE /contacts/{contactId}/skills/{skillId} : Delete a skill of an existing contact
   * Delete a skill of an existing contact of the connected user
   *
   * @param contactId Id of the contact to fetch (required)
   * @param skillId   Id of the skill to modify or delete (required)
   * @return contact&#39;s skill succesfully deleted (status code 200)
   * or Contact not found (status code 404)
   */
  @ApiOperation(value = "Delete a skill of an existing contact", nickname = "deleteContactSkill", notes = "Delete a skill of an existing contact of the connected user", authorizations = {
    @Authorization(value = "oauth2", scopes = {
      @AuthorizationScope(scope = "user", description = "Grants read/write access to user resources (his user info, his contacts and their skills)"),
      @AuthorizationScope(scope = "admin", description = "Grants read and write access to anything (his/others contacts and their skills, skills, users)")
    })
  }, tags = {"contactSkills",})
  @ApiResponses(value = {
    @ApiResponse(code = 200, message = "contact's skill succesfully deleted"),
    @ApiResponse(code = 404, message = "Contact not found")})
  @DeleteMapping(
    value = "/contacts/{contactId}/skills/{skillId}"
  )
  default ResponseEntity<Void> deleteContactSkill(@ApiParam(value = "Id of the contact to fetch", required = true) @PathVariable("contactId") Long contactId,
                                                  @ApiParam(value = "Id of the skill to modify or delete", required = true) @PathVariable("skillId") Long skillId) {
    return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

  }


  /**
   * GET /contacts/{id} : Get contact by id
   * Returns the contact corresponding to the received id of the connected user
   *
   * @param id Id of the contact to fetch (required)
   * @return search results matching criteria (status code 200)
   * or bad input parameter (status code 400)
   */
  @ApiOperation(value = "Get contact by id", nickname = "getContact", notes = "Returns the contact corresponding to the received id of the connected user", response = Contact.class, responseContainer = "List", authorizations = {
    @Authorization(value = "oauth2", scopes = {
      @AuthorizationScope(scope = "user", description = "Grants read/write access to user resources (his user info, his contacts and their skills)"),
      @AuthorizationScope(scope = "admin", description = "Grants read and write access to anything (his/others contacts and their skills, skills, users)")
    })
  }, tags = {"contacts",})
  @ApiResponses(value = {
    @ApiResponse(code = 200, message = "search results matching criteria", response = Contact.class, responseContainer = "List"),
    @ApiResponse(code = 400, message = "bad input parameter")})
  @GetMapping(
    value = "/contacts/{id}",
    produces = {"application/json"}
  )
  default ResponseEntity<List<Contact>> getContact(@ApiParam(value = "Id of the contact to fetch", required = true) @PathVariable("id") Long id) {
    getRequest().ifPresent(request -> {
      for (MediaType mediaType : MediaType.parseMediaTypes(request.getHeader("Accept"))) {
        if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
          String exampleString = "{ \"skills\" : [ { \"level\" : 5.637376656633329, \"skill\" : { \"name\" : \"SpringBoot\", \"id\" : 5, \"usersLevels\" : [ null, null ] }, \"id\" : 1 }, { \"level\" : 5.637376656633329, \"skill\" : { \"name\" : \"SpringBoot\", \"id\" : 5, \"usersLevels\" : [ null, null ] }, \"id\" : 1 } ], \"firstname\" : \"firstname\", \"address\" : \"address\", \"mobilephone\" : \"mobilephone\", \"id\" : 6, \"fullname\" : \"fullname\", \"email\" : \"email\", \"lastname\" : \"lastname\" }";
          ApiUtil.setExampleResponse(request, "application/json", exampleString);
          break;
        }
      }
    });
    return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

  }


  /**
   * GET /contacts : Get all contacts
     * A user can get his contacts. An admin can get all contacts.
   *
   * @param name Returns all contacts whose firstname or lastname contains the received string (optional)
   * @param email Returns all contacts whose email contains the received string (optional)
   * @return Search results matching criteria (status code 200)
   *         or bad input parameter (status code 400)
   */
  @ApiOperation(value = "Get all contacts", nickname = "getContacts", notes = "A user can get his contacts. An admin can get all contacts.", response = Contact.class, responseContainer = "List", authorizations = {
    @Authorization(value = "oauth2", scopes = {
      @AuthorizationScope(scope = "user", description = "Grants read/write access to user resources (his user info, his contacts and their skills)"),
      @AuthorizationScope(scope = "admin", description = "Grants read and write access to anything (his/others contacts and their skills, skills, users)")
    })
  }, tags = {"contacts",})
  @ApiResponses(value = {
    @ApiResponse(code = 200, message = "Search results matching criteria", response = Contact.class, responseContainer = "List"),
    @ApiResponse(code = 400, message = "bad input parameter")})
  @GetMapping(
    value = "/contacts",
    produces = {"application/json"}
  )
  default ResponseEntity<List<Contact>> getContacts(@ApiParam(value = "Returns all contacts whose firstname or lastname contains the received string") @Valid @RequestParam(value = "name", required = false) String name,
                                                    @ApiParam(value = "Returns all contacts whose email contains the received string") @Valid @RequestParam(value = "email", required = false) String email) {
    getRequest().ifPresent(request -> {
      for (MediaType mediaType : MediaType.parseMediaTypes(request.getHeader("Accept"))) {
        if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
          String exampleString = "{ \"skills\" : [ { \"level\" : 5.637376656633329, \"skill\" : { \"name\" : \"SpringBoot\", \"id\" : 5, \"usersLevels\" : [ null, null ] }, \"id\" : 1 }, { \"level\" : 5.637376656633329, \"skill\" : { \"name\" : \"SpringBoot\", \"id\" : 5, \"usersLevels\" : [ null, null ] }, \"id\" : 1 } ], \"firstname\" : \"firstname\", \"address\" : \"address\", \"mobilephone\" : \"mobilephone\", \"id\" : 6, \"fullname\" : \"fullname\", \"email\" : \"email\", \"lastname\" : \"lastname\" }";
          ApiUtil.setExampleResponse(request, "application/json", exampleString);
          break;
        }
      }
    });
    return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

  }


  /**
   * PUT /contacts/{id} : Update an existing contact
   * Update an existing contact of the connected user
   *
   * @param id Id of the contact to fetch (required)
   * @param contactToUpdate Contact to update (optional)
   * @return contact succesfully updated (status code 200)
   *         or Invalid contact supplied (status code 400)
   *         or Contact not found (status code 404)
   */
  @ApiOperation(value = "Update an existing contact", nickname = "updateContact", notes = "Update an existing contact of the connected user", response = Contact.class, responseContainer = "List", authorizations = {
    @Authorization(value = "oauth2", scopes = {
      @AuthorizationScope(scope = "user", description = "Grants read/write access to user resources (his user info, his contacts and their skills)"),
      @AuthorizationScope(scope = "admin", description = "Grants read and write access to anything (his/others contacts and their skills, skills, users)")
    })
  }, tags = {"contacts",})
  @ApiResponses(value = {
    @ApiResponse(code = 200, message = "contact succesfully updated", response = Contact.class, responseContainer = "List"),
    @ApiResponse(code = 400, message = "Invalid contact supplied"),
    @ApiResponse(code = 404, message = "Contact not found")})
  @PutMapping(
    value = "/contacts/{id}",
    produces = {"application/json"},
    consumes = {"application/json"}
  )
  default ResponseEntity<List<Contact>> updateContact(@ApiParam(value = "Id of the contact to fetch", required = true) @PathVariable("id") Long id,
                                                      @ApiParam(value = "Contact to update") @Valid @RequestBody(required = false) ContactToUpdate contactToUpdate) {
    getRequest().ifPresent(request -> {
      for (MediaType mediaType : MediaType.parseMediaTypes(request.getHeader("Accept"))) {
        if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
          String exampleString = "{ \"skills\" : [ { \"level\" : 5.637376656633329, \"skill\" : { \"name\" : \"SpringBoot\", \"id\" : 5, \"usersLevels\" : [ null, null ] }, \"id\" : 1 }, { \"level\" : 5.637376656633329, \"skill\" : { \"name\" : \"SpringBoot\", \"id\" : 5, \"usersLevels\" : [ null, null ] }, \"id\" : 1 } ], \"firstname\" : \"firstname\", \"address\" : \"address\", \"mobilephone\" : \"mobilephone\", \"id\" : 6, \"fullname\" : \"fullname\", \"email\" : \"email\", \"lastname\" : \"lastname\" }";
          ApiUtil.setExampleResponse(request, "application/json", exampleString);
          break;
        }
      }
    });
    return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

  }


  /**
   * PUT /contacts/{contactId}/skills/{skillId} : Update a skill of a contact
   * Update the skill level of an existing contact of the connected user
   *
   * @param contactId          Id of the contact to fetch (required)
   * @param skillId            Id of the skill to modify or delete (required)
   * @param skillLevelToUpdate Skill level to update (optional)
   * @return Contact&#39;s skill level succesfully updated (status code 200)
   * or Invalid skill lever supplied (status code 400)
   * or Contact or skill not found (status code 404)
   */
  @ApiOperation(value = "Update a skill of a contact", nickname = "updateContactSkill", notes = "Update the skill level of an existing contact of the connected user", response = Contact.class, responseContainer = "List", authorizations = {
    @Authorization(value = "oauth2", scopes = {
      @AuthorizationScope(scope = "user", description = "Grants read/write access to user resources (his user info, his contacts and their skills)"),
      @AuthorizationScope(scope = "admin", description = "Grants read and write access to anything (his/others contacts and their skills, skills, users)")
    })
  }, tags = {"contactSkills",})
  @ApiResponses(value = {
    @ApiResponse(code = 200, message = "Contact's skill level succesfully updated", response = Contact.class, responseContainer = "List"),
    @ApiResponse(code = 400, message = "Invalid skill lever supplied"),
    @ApiResponse(code = 404, message = "Contact or skill not found")})
  @PutMapping(
    value = "/contacts/{contactId}/skills/{skillId}",
    produces = {"application/json"},
    consumes = {"application/json"}
  )
  default ResponseEntity<List<Contact>> updateContactSkill(@ApiParam(value = "Id of the contact to fetch", required = true) @PathVariable("contactId") Long contactId,
                                                           @ApiParam(value = "Id of the skill to modify or delete", required = true) @PathVariable("skillId") Long skillId,
                                                           @ApiParam(value = "Skill level to update") @Valid @RequestBody(required = false) SkillLevelToUpdate skillLevelToUpdate) {
    getRequest().ifPresent(request -> {
      for (MediaType mediaType : MediaType.parseMediaTypes(request.getHeader("Accept"))) {
        if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
          String exampleString = "{ \"skills\" : [ { \"level\" : 5.637376656633329, \"skill\" : { \"name\" : \"SpringBoot\", \"id\" : 5, \"usersLevels\" : [ null, null ] }, \"id\" : 1 }, { \"level\" : 5.637376656633329, \"skill\" : { \"name\" : \"SpringBoot\", \"id\" : 5, \"usersLevels\" : [ null, null ] }, \"id\" : 1 } ], \"firstname\" : \"firstname\", \"address\" : \"address\", \"mobilephone\" : \"mobilephone\", \"id\" : 6, \"fullname\" : \"fullname\", \"email\" : \"email\", \"lastname\" : \"lastname\" }";
          ApiUtil.setExampleResponse(request, "application/json", exampleString);
          break;
        }
      }
    });
    return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

  }

}