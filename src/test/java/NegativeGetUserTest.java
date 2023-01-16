import static org.hamcrest.Matchers.equalTo;

import org.junit.jupiter.api.Test;
import services.UserApi;

public class NegativeGetUserTest {

  private final UserApi userApi = new UserApi();

  /**
   * Проверка поиска несуществуюещего пользователя Действие: - Отправляем get запрос на url
   * "/user/{username}" с заведомо несуществующим username Ожидаемый результат: - Получим статус 404
   * - В ответе "type" будет равен "error" - В ответе "message" будет равен "User not found"
   */
  @Test
  public void getUserNotFoundTest() {
    userApi
        .getUserByName("notExistingUser")
        .statusCode(404)
        .body("type", equalTo("error"))
        .body("message", equalTo("User not found"));
  }
}
