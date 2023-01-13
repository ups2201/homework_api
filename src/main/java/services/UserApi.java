package services;

import dto.UserDTO;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class UserApi extends BaseApi {
  public static final String USER_URL = "/user";

  public ValidatableResponse createUser(UserDTO userDTO) {
    return given(specification)
              .body(userDTO)
            .when()
              .post(USER_URL)
            .then()
              .log().all();
  }
  public ValidatableResponse getUserByName(String name) {
    return given(specification)
            .basePath(USER_URL + "/{username}")
            .pathParam("username", name)
            .when()
            .get()
            .then()
            .log().all();
  }

}
