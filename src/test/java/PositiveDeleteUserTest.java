import dto.UserDTO;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.ValidatableResponse;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import services.UserApi;

public class PositiveDeleteUserTest {

  private final UserApi userApi = new UserApi();

  /**
   * Проверка успешного получения пользователя Действие: - Создаём пользователя c username =
   * "UserForDeleting" - Провряем, что созданный пользователь существует, при выполнении
   * getUserByName(username) возвращается статус 200 - Удаляем пользователя с username =
   * "UserForDeleting" Ожидаемый результат: - статус ответа равен 200 - схема ответа соответсвует
   * jsonSchema 'deleteUserSchema.json' - пользователя не существует, на getUserByName(username)
   * возвращается статус 404
   */
  @Test
  public void deleteUserSuccessTest() {

    String username = "UserForDeleting";
    UserDTO userDTO =
        UserDTO.builder()
            .id(2L)
            .username(username)
            .firstName("firstName")
            .lastName("lastName")
            .phone("79112223344")
            .email("mail@mail.ru")
            .password("qwerty")
            .userStatus(200L)
            .build();

    userApi.createUser(userDTO).statusCode(200);
    userApi.getUserByName(username).statusCode(200);

    ValidatableResponse validatableResponse = userApi.deleteUserByName(username).statusCode(200);
    String userBodyResponse = validatableResponse.extract().response().body().prettyPrint();

    SoftAssertions softAssertions = new SoftAssertions();

    softAssertions
        .assertThat(
            JsonSchemaValidator.matchesJsonSchemaInClasspath("deleteUserSchema.json")
                .matches(userBodyResponse))
        .withFailMessage("Не совпадает схема ответа")
        .isTrue();

    softAssertions
        .assertThat(userApi.getUserByName(username).extract().statusCode())
        .withFailMessage("Пользователь существует, удаление не сработало")
        .isEqualTo(404);
    softAssertions.assertAll();
  }
}
