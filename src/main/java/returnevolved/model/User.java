package returnevolved.model;

import org.hibernate.annotations.GenericGenerator;

import java.util.List;
import java.util.UUID;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "\"user\"")
public class User {

  @Id
  @GeneratedValue(generator = "uuid2")
  @GenericGenerator(name="uuid2", strategy = "uuid2")
  private UUID id;

  @Size(min = 4, max = 255, message = "Minimum username length: 4 characters")
  @Column(unique = true, nullable = false)
  private String username;

  @Column(unique = true, nullable = false)
  private String email;

  @Size(min = 8, message = "Minimum password length: 8 characters")
  private String password;

  @ElementCollection(fetch = FetchType.EAGER)
  private List<Role> roles;

  @OneToMany
  private List<Recipe> recipes;

  public User() {
  }

  public User(UUID id, String username, String email, String password, List<Role> roles, List<Recipe> recipes) {
    this.id = id;
    this.username = username;
    this.email = email;
    this.password = password;
    this.roles = roles;
    this.recipes = recipes;
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

  public List<Recipe> getRecipes() {
    return recipes;
  }

  public void setRecipes(List<Recipe> recipes) {
    this.recipes = recipes;
  }

  @Override
  public String toString() {
    return "User{" +
            "id=" + id +
            ", username='" + username + '\'' +
            ", email='" + email + '\'' +
            ", password='" + password + '\'' +
            ", roles=" + roles +
            ", recipes=" + recipes.size() +
            '}';
  }
}
