package services;

import static io.restassured.RestAssured.given;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class BaseApi {
  public static final String BASE_URI = "https://petstore.swagger.io/v2";

  public final RequestSpecification specification;

  public BaseApi() {
    this.specification = given().baseUri(BASE_URI).contentType(ContentType.JSON).log().all();
  }
}
