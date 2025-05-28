package api.steps;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import models.UserModel;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static models.EndPoints.CREATE_USER_PATH;
import static models.EndPoints.DELETE_USER_PATH;

public class UserApiSteps {
    public static Response createUser(UserModel user) {
        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site";
        return given()
                .header("Content-Type", "application/json")
                .body(user)
                .when()
                .post(CREATE_USER_PATH);

    }

    public static void deleteUser(String userToken) {
        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site";
        given()
                .header("Authorization", userToken)
                .when()
                .delete(DELETE_USER_PATH)
                .then()
                .extract().response();
    }
    public static String generateUniqueEmail() {
        return "user_" + UUID.randomUUID() + "@example.com";
    }
    public static String generateUniquePassword() {
        return UUID.randomUUID().toString().substring(6);
    }

    public static String generateUniqueName() {
        return "user_" + UUID.randomUUID();
    }
}
