/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (5.0.0-SNAPSHOT).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package ch.benoitschopfer.controller;

import ch.benoitschopfer.model.DTO.UserToAddOrUpdate;
import ch.benoitschopfer.model.User;
import io.swagger.annotations.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.NativeWebRequest;

import javax.validation.Valid;
import java.util.Optional;

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2020-09-24T16:13:09.139748+02:00[Europe/Paris]")
@Validated
@Api(value = "users", description = "the users API")
public interface UsersApi {

  default Optional<NativeWebRequest> getRequest() {
    return Optional.empty();
  }

  /**
   * DELETE /users/{id} : Delete an existing user.
   * Delete the connected user. Only an admin can delete other user than himself.
   *
   * @param id Id of the user to delete (required).
   * @return User succesfully deleted (status code 204) or User not found (status code 404).
   */
  @ApiOperation(value = "Delete an existing user.", nickname = "deleteUser", notes = "Delete the connected user. Only an admin can delete other user than himself.", authorizations = {
    @Authorization(value = "oauth2", scopes = {
      @AuthorizationScope(scope = "user", description = "Grants read/write access to user resources (his user info, his contacts and their skills)."),
      @AuthorizationScope(scope = "admin", description = "Grants read and write access to anything (his/others contacts and their skills, skills, users).")
    })
  }, tags = {"users",})
  @ApiResponses(value = {
    @ApiResponse(code = 204, message = "User succesfully deleted."),
    @ApiResponse(code = 404, message = "User not found.")})
  @DeleteMapping(
    value = "/users/{id}"
  )
  default ResponseEntity<Void> deleteUser(@ApiParam(value = "Id of the user to delete.", required = true) @PathVariable("id") long id) {
    return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

  }


  /**
   * GET /users/{id} : Get user by id.
   * Returns the user corresponding to the received id. It can only be himself if the connected user is a normal user. It can be any user if the connected user is an admin.
   *
   * @param id Id of the user to fetch (required).
   * @return search results matching criteria (status code 200) or bad input parameter (status code 400).
   */
  @ApiOperation(value = "Get user by id.", nickname = "getUser", notes = "Returns the user corresponding to the received name. It can only be himself if the connected user is a normal user. It can be any user if the connected user is an admin.", response = User.class, authorizations = {
    @Authorization(value = "oauth2", scopes = {
      @AuthorizationScope(scope = "user", description = "Grants read/write access to user resources (his user info, his contacts and their skills)."),
      @AuthorizationScope(scope = "admin", description = "Grants read and write access to anything (his/others contacts and their skills, skills, users).")
    })
  }, tags = {"users",})
  @ApiResponses(value = {
    @ApiResponse(code = 200, message = "The user corresponding to the received id.", response = User.class),
    @ApiResponse(code = 400, message = "Bad input parameter.")})
  @GetMapping(
    value = "/users/{id}",
    produces = {"application/json"}
  )
  default ResponseEntity<User> getUser(@ApiParam(value = "Id of the user to fetch", required = true) @PathVariable("id") long id) {
    getRequest().ifPresent(request -> {
      for (MediaType mediaType : MediaType.parseMediaTypes(request.getHeader("Accept"))) {
        if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
          String exampleString = "{ \"password\" : \"password\", \"rights\" : \"rights\", \"id\" : 0, \"email\" : \"email\", \"contacts\" : [ { \"skills\" : [ { \"level\" : 5.637376656633329, \"skill\" : { \"name\" : \"SpringBoot\", \"id\" : 5, \"usersLevels\" : [ null, null ] }, \"id\" : 1 }, { \"level\" : 5.637376656633329, \"skill\" : { \"name\" : \"SpringBoot\", \"id\" : 5, \"usersLevels\" : [ null, null ] }, \"id\" : 1 } ], \"firstname\" : \"firstname\", \"address\" : \"address\", \"mobilephone\" : \"mobilephone\", \"id\" : 6, \"fullname\" : \"fullname\", \"email\" : \"email\", \"lastname\" : \"lastname\" }, { \"skills\" : [ { \"level\" : 5.637376656633329, \"skill\" : { \"name\" : \"SpringBoot\", \"id\" : 5, \"usersLevels\" : [ null, null ] }, \"id\" : 1 }, { \"level\" : 5.637376656633329, \"skill\" : { \"name\" : \"SpringBoot\", \"id\" : 5, \"usersLevels\" : [ null, null ] }, \"id\" : 1 } ], \"firstname\" : \"firstname\", \"address\" : \"address\", \"mobilephone\" : \"mobilephone\", \"id\" : 6, \"fullname\" : \"fullname\", \"email\" : \"email\", \"lastname\" : \"lastname\" } ] }";
          ApiUtil.setExampleResponse(request, "application/json", exampleString);
          break;
        }
      }
    });
    return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

  }


  /**
   * GET /users : Get all users
   * By passing in the appropriate options, admins can search for specifics users in the system.
   *
   * @param email Returns all users whose email contains the received string (optional).
   * @return Every users whose email contains the received string (status code 200) or bad input parameter (status code 400).
   */
  @ApiOperation(value = "Get all users.", nickname = "getUsers", notes = "By passing in the appropriate options, admins can search for specifics users in the system.", response = User.class, responseContainer = "List", authorizations = {
    @Authorization(value = "oauth2", scopes = {
      @AuthorizationScope(scope = "admin", description = "Grants read and write access to anything (his/others contacts and their skills, skills, users).")
    })
  }, tags = {"users",})
  @ApiResponses(value = {
    @ApiResponse(code = 200, message = "Every users whose email contains the received string.", response = User.class, responseContainer = "List"),
    @ApiResponse(code = 400, message = "bad input parameter.")})
  @GetMapping(
    value = "/users",
    produces = {"application/json"}
  )
  default ResponseEntity<Page<User>> getUsers(@ApiParam(value = "Returns all users whose email contains the received string.") @Valid @RequestParam(value = "email", required = false) String email, Pageable Pageable) {
    getRequest().ifPresent(request -> {
      for (MediaType mediaType : MediaType.parseMediaTypes(request.getHeader("Accept"))) {
        if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
          String exampleString = "{ \"password\" : \"password\", \"rights\" : \"rights\", \"id\" : 0, \"email\" : \"email\", \"contacts\" : [ { \"skills\" : [ { \"level\" : 5.637376656633329, \"skill\" : { \"name\" : \"SpringBoot\", \"id\" : 5, \"usersLevels\" : [ null, null ] }, \"id\" : 1 }, { \"level\" : 5.637376656633329, \"skill\" : { \"name\" : \"SpringBoot\", \"id\" : 5, \"usersLevels\" : [ null, null ] }, \"id\" : 1 } ], \"firstname\" : \"firstname\", \"address\" : \"address\", \"mobilephone\" : \"mobilephone\", \"id\" : 6, \"fullname\" : \"fullname\", \"email\" : \"email\", \"lastname\" : \"lastname\" }, { \"skills\" : [ { \"level\" : 5.637376656633329, \"skill\" : { \"name\" : \"SpringBoot\", \"id\" : 5, \"usersLevels\" : [ null, null ] }, \"id\" : 1 }, { \"level\" : 5.637376656633329, \"skill\" : { \"name\" : \"SpringBoot\", \"id\" : 5, \"usersLevels\" : [ null, null ] }, \"id\" : 1 } ], \"firstname\" : \"firstname\", \"address\" : \"address\", \"mobilephone\" : \"mobilephone\", \"id\" : 6, \"fullname\" : \"fullname\", \"email\" : \"email\", \"lastname\" : \"lastname\" } ] }";
          ApiUtil.setExampleResponse(request, "application/json", exampleString);
          break;
        }
      }
    });
    return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

  }


  /**
   * PUT /users/{id} : Update an existing user.
   * Update the connected user. It can only be himself if the connected user is a normal user. It can be any user if the connected user is an admin.
   *
   * @param id           Id of the user to update (required).
   * @param userToAddOrUpdate User to update (optional).
   * @return User succesfully updated (status code 200) or Invalid user supplied (status code 400) or User not found (status code 404).
   */
  @ApiOperation(value = "Update an existing user.", nickname = "updateUser", notes = "Update the connected user. It can only be himself if the connected user is a normal user. It can be any user if the connected user is an admin.", response = User.class, authorizations = {
    @Authorization(value = "oauth2", scopes = {
      @AuthorizationScope(scope = "user", description = "Grants read/write access to user resources (his user info, his contacts and their skills)."),
      @AuthorizationScope(scope = "admin", description = "Grants read and write access to anything (his/others contacts and their skills, skills, users).")
    })
  }, tags = {"users",})
  @ApiResponses(value = {
    @ApiResponse(code = 200, message = "User succesfully updated.", response = User.class, responseContainer = "List"),
    @ApiResponse(code = 400, message = "Invalid user supplied."),
    @ApiResponse(code = 404, message = "User not found.")})
  @PutMapping(
    value = "/users/{id}",
    produces = {"application/json"},
    consumes = {"application/json"}
  )
  default ResponseEntity<User> updateUser(@ApiParam(value = "Id of the user to update.", required = true) @PathVariable("id") long id,
                                          @ApiParam(value = "User to update.") @RequestBody(required = false) UserToAddOrUpdate userToAddOrUpdate) {
    getRequest().ifPresent(request -> {
      for (MediaType mediaType : MediaType.parseMediaTypes(request.getHeader("Accept"))) {
        if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
          String exampleString = "{ \"password\" : \"password\", \"rights\" : \"rights\", \"id\" : 0, \"email\" : \"email\", \"contacts\" : [ { \"skills\" : [ { \"level\" : 5.637376656633329, \"skill\" : { \"name\" : \"SpringBoot\", \"id\" : 5, \"usersLevels\" : [ null, null ] }, \"id\" : 1 }, { \"level\" : 5.637376656633329, \"skill\" : { \"name\" : \"SpringBoot\", \"id\" : 5, \"usersLevels\" : [ null, null ] }, \"id\" : 1 } ], \"firstname\" : \"firstname\", \"address\" : \"address\", \"mobilephone\" : \"mobilephone\", \"id\" : 6, \"fullname\" : \"fullname\", \"email\" : \"email\", \"lastname\" : \"lastname\" }, { \"skills\" : [ { \"level\" : 5.637376656633329, \"skill\" : { \"name\" : \"SpringBoot\", \"id\" : 5, \"usersLevels\" : [ null, null ] }, \"id\" : 1 }, { \"level\" : 5.637376656633329, \"skill\" : { \"name\" : \"SpringBoot\", \"id\" : 5, \"usersLevels\" : [ null, null ] }, \"id\" : 1 } ], \"firstname\" : \"firstname\", \"address\" : \"address\", \"mobilephone\" : \"mobilephone\", \"id\" : 6, \"fullname\" : \"fullname\", \"email\" : \"email\", \"lastname\" : \"lastname\" } ] }";
          ApiUtil.setExampleResponse(request, "application/json", exampleString);
          break;
        }
      }
    });
    return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

  }

}
