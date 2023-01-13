package services;

import dto.UserDTO;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class BaseApi {
  public static final String BASE_URI = "https://petstore.swagger.io/v2";

  public final RequestSpecification specification;

  public BaseApi() {
    this.specification = given()
            .baseUri(BASE_URI)
            .contentType(ContentType.JSON)
            .log().all();
  }
}
