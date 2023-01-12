import dto.UserDTO;
import org.junit.jupiter.api.Test;
import services.UserApi;

import static org.hamcrest.Matchers.equalTo;

public class CreateUserTest {

    @Test
    public void checkCreateUser() {
        UserApi userApi = new UserApi();
        long id = 202;

        UserDTO user = UserDTO.builder()
                        .id(id)
                        .firstName("firstName")
                        .lastName("lastName")
                        .userStatus(200l)
                        .phone("79112223344")
                        .email("mail@mail.ru")
                        .build();


        userApi.createUser(user)
                .statusCode(200)
                .body("message", equalTo(String.valueOf(id)))
                .body("type", equalTo("unknown"));

    }
}
