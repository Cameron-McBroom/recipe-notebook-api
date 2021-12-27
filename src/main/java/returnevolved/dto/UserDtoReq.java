package returnevolved.dto;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import returnevolved.model.Role;

import javax.validation.constraints.NotNull;

public class UserDtoReq {
  
  private String username;
  private String email;
  private String password;

  @NotNull
  List<Role> roles;

  public UserDtoReq() {
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

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public List<Role> getRoles() {
    return roles;
  }

  public void setRoles(List<Role> roles) {
    this.roles = roles;
  }
}
