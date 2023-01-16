import org.junit.jupiter.api.Test;
import services.UserApi;

public class NegativeDeleteUserTest {

  private final UserApi userApi = new UserApi();

  /**
   * Проверка удаления несуществуюещего пользователя Действие:
   * - Отправляем delete запрос на url "/user/{username}" с заведомо несуществующим username
   * Ожидаемый результат:
   * - Получим статус 404
   */
  @Test
  public void deleteUserNotFoundTest() {
    userApi.deleteUserByName("notExistingUser").statusCode(404);
  }
}
