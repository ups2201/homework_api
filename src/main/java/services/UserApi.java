package services;

import static io.restassured.RestAssured.given;

import dto.UserDTO;
import io.restassured.response.ValidatableResponse;

public class UserApi extends BaseApi {
  public static final String USER_URL = "/user";

  public ValidatableResponse createUser(UserDTO userDTO) {
    return given(specification).body(userDTO).when().post(USER_URL).then().log().all();
  }

  public ValidatableResponse getUserByName(String name) {
    return given(specification)
        .basePath(USER_URL + "/{username}")
        .pathParam("username", name)
        .when()
        .get()
        .then()
        .log()
        .all();
  }

  public ValidatableResponse deleteUserByName(String name) {
    return given(specification)
        .basePath(USER_URL + "/{username}")
        .pathParam("username", name)
        .when()
        .delete()
        .then()
        .log()
        .all();
  }
}
