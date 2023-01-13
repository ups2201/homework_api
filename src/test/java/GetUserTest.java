import dto.UserDTO;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.ValidatableResponse;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import services.UserApi;

public class GetUserTest {

    @Test
    public void getUserTest() {
        UserApi userApi = new UserApi();

        String username = "Alex";
        UserDTO userDTO = UserDTO.builder()
                .id(1L)
                .username(username)
                .firstName("firstName")
                .lastName("lastName")
                .phone("79112223344")
                .email("mail@mail.ru")
                .password("qwerty")
                .userStatus(200L)
                .build();

        userApi.createUser(userDTO).statusCode(200);

        ValidatableResponse validatableResponse = userApi.getUserByName(username).statusCode(200);

        UserDTO user = validatableResponse.extract().body().as(UserDTO.class);
        String userBodyResponse = validatableResponse.extract().response().body().prettyPrint();

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(username)
                .withFailMessage(String.format("Не совпадает отправленный '%s' и полученный '%s' username", username, user.getUsername()))
                .isEqualTo(user.getUsername());

        softAssertions.assertThat(JsonSchemaValidator
                        .matchesJsonSchemaInClasspath("getUserSchema.json").matches(userBodyResponse))
                .withFailMessage("Не совпадает схема ответа")
                .isTrue();

        softAssertions.assertAll();
    }
}
