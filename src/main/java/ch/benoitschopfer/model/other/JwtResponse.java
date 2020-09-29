package ch.benoitschopfer.model.other;

import java.io.Serializable;
import java.util.List;

public class JwtResponse implements Serializable {
  private final String token;
  private final String type = "Bearer";
  private final Long id;
  private final String email;
  private final List<String> roles;

  public JwtResponse(String token, Long id, String email, List<String> roles) {
    this.token = token;
    this.id = id;
    this.email = email;
    this.roles = roles;
  }

  public String getAccessToken() {
    return token;
  }

  public String getTokenType() {
    return type;
  }

  public Long getId() {
    return id;
  }

  public String getEmail() {
    return email;
  }

  public List<String> getRoles() {
    return roles;
  }

}
