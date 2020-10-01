package ch.benoitschopfer.model.other;

import ch.benoitschopfer.model.entity.User;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class JwtResponse implements Serializable {
  @JsonProperty("accessToken")
  private final String token;

  @JsonProperty("tokenType")
  private final String tokenType = "Bearer";

  @JsonProperty("user")
  private final User user;

  public JwtResponse(String token, User user) {
    this.token = token;
    this.user = user;
  }

  public String getAccessToken() {
    return token;
  }

  public String getTokenType() {
    return tokenType;
  }

  public User getUser() {
    return user;
  }
}
