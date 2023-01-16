import dto.UserDTO;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.ValidatableResponse;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import services.UserApi;

public class PositiveGetUserTest {

  private final UserApi userApi = new UserApi();

  /**
   * Проверка успешного получения пользователя Действие: - Создаём пользователя c username = "Alex"
   * - Для получения пользователя отправляем get запрос на url /user/{username}, в параметрах
   * передаём значение "Alex" Ожидаемый результат: - статус ответа равен 200 - в ответе username
   * совпадает с username, который указывали при создании пользователя - схема ответа соответсвует
   * jsonSchema 'getUserSchema.json'
   */
  @Test
  public void getUserSuccessTest() {

    String username = "Alex";
    UserDTO userDTO =
        UserDTO.builder()
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
    softAssertions
        .assertThat(user)
        .withFailMessage(
            String.format(
                "Не совпадает отправленный '%s' и полученный '%s' пользователь", user, userDTO))
        .isEqualTo(userDTO);

    softAssertions
        .assertThat(
            JsonSchemaValidator.matchesJsonSchemaInClasspath("getUserSchema.json")
                .matches(userBodyResponse))
        .withFailMessage("Не совпадает схема ответа")
        .isTrue();

    softAssertions.assertAll();
  }
}
