package dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.util.Objects;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonSerialize
@JsonDeserialize
public class UserDTO {

  private Long id;
  private String username;
  private String firstName;
  private String lastName;
  private String phone;
  private String email;
  private String password;
  private Long userStatus;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    UserDTO userDTO = (UserDTO) o;
    return
        id.equals(userDTO.id) &&
        username.equals(userDTO.username) &&
        firstName.equals(userDTO.firstName) &&
        lastName.equals(userDTO.lastName) &&
        phone.equals(userDTO.phone) &&
        email.equals(userDTO.email) &&
        password.equals(userDTO.password) &&
        userStatus.equals(userDTO.userStatus);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, username, firstName, lastName, phone, email, password, userStatus);
  }
}
