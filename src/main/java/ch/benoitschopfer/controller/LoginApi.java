/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (5.0.0-SNAPSHOT).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package ch.benoitschopfer.controller;

import ch.benoitschopfer.model.DTO.UserAddOrUpdate;
import ch.benoitschopfer.model.User;
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
@Api(value = "login", description = "the login API")
public interface LoginApi {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

  /**
   * POST /login : Logs in an user
   * Logs in an user with oauth authentication
   *
   * @param userAddOrUpdate A JSON object containing the username and the password of the user to log in (required)
   * @return Successfully authenticated (status code 200)
   * or Invalid input, received object is invalid (status code 400)
   * or Unauthorized, login failed (status code 401)
   * or User not found, unknown username (status code 404)
   */
  @ApiOperation(value = "Logs in an user", nickname = "login", notes = "Logs in an user with oauth authentication", response = User.class, responseContainer = "List", tags = {
    "login",})
  @ApiResponses(value = {
    @ApiResponse(code = 200, message = "Successfully authenticated", response = User.class, responseContainer = "List"),
    @ApiResponse(code = 400, message = "Invalid input, received object is invalid"),
    @ApiResponse(code = 401, message = "Unauthorized, login failed"),
    @ApiResponse(code = 404, message = "User not found, unknown username")})
  @PostMapping(
    value = "/login",
    produces = {"application/json"},
    consumes = {"application/json"}
  )
  default ResponseEntity<List<User>> login(@ApiParam(value = "A JSON object containing the username and the password of the user to log in", required = true) @Valid @RequestBody UserAddOrUpdate userAddOrUpdate) {
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
