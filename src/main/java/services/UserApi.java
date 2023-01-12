package services;

import dto.UserDTO;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class UserApi {
  public static final String BASE_URI = "https://petstore.swagger.io/v2";
  public static final String USER_URL = "/user";

  private final RequestSpecification specification;

  public UserApi() {
    specification = given()
            .baseUri(BASE_URI)
            .contentType(ContentType.JSON)
            .log().all();
  }

  public ValidatableResponse createUser(UserDTO userDTO) {
    return given(specification)
            .body(userDTO)
            .when()
            .post(USER_URL)
            .then()
            .log().all();
  }
}
