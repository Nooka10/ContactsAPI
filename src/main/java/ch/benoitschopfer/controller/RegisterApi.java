/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (5.0.0-SNAPSHOT).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package ch.benoitschopfer.controller;

import ch.benoitschopfer.model.User;
import ch.benoitschopfer.model.UserToAdd;
import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.context.request.NativeWebRequest;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2020-09-24T16:13:09.139748+02:00[Europe/Paris]")
@Validated
@Api(value = "register", description = "the register API")
public interface RegisterApi {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

  /**
   * POST /register : Register a new user
   * Register a new user in the system and logs him in
   *
   * @param userToAdd A JSON object containing the username and the password of the new user (required)
   * @return User successfully created (status code 201)
   * or Invalid input, received object is invalid (status code 400)
   * or This email adress is already used (status code 409)
   */
  @ApiOperation(value = "Register a new user", nickname = "register", notes = "Register a new user in the system and logs him in", response = User.class, responseContainer = "List", tags = {
    "login",})
  @ApiResponses(value = {
    @ApiResponse(code = 201, message = "User successfully created", response = User.class, responseContainer = "List"),
    @ApiResponse(code = 400, message = "Invalid input, received object is invalid"),
    @ApiResponse(code = 409, message = "This email adress is already used")})
  @PostMapping(
    value = "/register",
    produces = {"application/json"},
    consumes = {"application/json"}
  )
  default ResponseEntity<List<User>> register(@ApiParam(value = "A JSON object containing the username and the password of the new user", required = true) @Valid @RequestBody UserToAdd userToAdd) {
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