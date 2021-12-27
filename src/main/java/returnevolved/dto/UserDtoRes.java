package returnevolved.dto;

import java.util.List;
import java.util.UUID;

import returnevolved.model.Role;


public class UserDtoRes {

  private UUID id;
  private String username;
  private String email;
  List<Role> roles;

  public UserDtoRes() {
  }

  public UserDtoRes(UUID id, String username, String email, List<Role> roles) {
    this.id = id;
    this.username = username;
    this.email = email;
    this.roles = roles;
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public List<Role> getRoles() {
    return roles;
  }

  public void setRoles(List<Role> roles) {
    this.roles = roles;
  }
}
