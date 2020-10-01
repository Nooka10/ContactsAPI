package ch.benoitschopfer.model.other;

import ch.benoitschopfer.service.UserDetailsImpl;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class JwtResponse implements Serializable {
  @JsonProperty("accessToken")
  private final String token;

  @JsonProperty("tokenType")
  private final String tokenType = "Bearer";

  @JsonProperty("user")
  private final UserDetailsImpl user;

  public JwtResponse(String token, UserDetailsImpl user) {
    this.token = token;
    this.user = user;
  }

  public String getAccessToken() {
    return token;
  }

  public String getTokenType() {
    return tokenType;
  }

  private UserDetailsImpl getUser() {
    return user;
  }
}
