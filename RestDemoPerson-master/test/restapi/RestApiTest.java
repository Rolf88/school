package restapi;

import static com.jayway.restassured.RestAssured.basePath;
import static com.jayway.restassured.RestAssured.baseURI;
import static com.jayway.restassured.RestAssured.defaultParser;
import static com.jayway.restassured.RestAssured.given;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.parsing.Parser;

import facade.PersonFacade;
import javax.persistence.EntityManagerFactory;
import static org.hamcrest.Matchers.equalTo;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

public class RestApiTest {

  static EntityManagerFactory emfOriginal;
  static PersonFacade facade;
  int idForOle;

  public RestApiTest() {
  }

  @BeforeClass
  public static void setupAll() {
    baseURI = "http://localhost:8084/REstCRUD_JAXRS";
    defaultParser = Parser.JSON;
    basePath = "/api";
  }

  
  @Test
  public void AddNewPersonAndVerifyHiExist() {

    int id
            = given()
            .contentType("application/json")
            .body("{\"fName\": \"a\", \"lName\": \"a\", \"phone\": \"123\"}")
            .when()
            .post("/person")
            .then().
            statusCode(200).and().body("fName", equalTo("a")).
            extract().path("id");

    given().
            accept(ContentType.JSON).
            when().
            get("/person/" + id).
            then().
            statusCode(200).and().body("fName", equalTo("a")).and().body("phone", equalTo("123"));
  }

  @Test
  public void NonExistingPerson() {
    given().
            when().
            get("/person/6345487").
            then().
            statusCode(404).and().body("code", equalTo(404)).and().body("message", equalTo("No Person found with provided id"));
  }

  @Test
  public void NonExistingService() {
    given().
            when().
            get("/i_dont_exist").
            then().
            statusCode(404).and().body("code", equalTo(404));
  }
}
