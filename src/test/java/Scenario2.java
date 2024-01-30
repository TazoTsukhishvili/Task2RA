import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.annotations.Test;

import java.util.Random;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.*;

public class Scenario2 {
    @Test (description =
            "Add user\n" +
            "Check that books list equals to null\n" +
            "Check username value\n")
    public void createUser1() throws JSONException {
        String user = Scenario2Data.getUser2Json(Scenario2Data.RandomUserName);

        RestAssured.baseURI = TestConfigData.userLink;
        Response response = given()
                .header(TestConfigData.accept, TestConfigData.appJson)
                .header(TestConfigData.contentType, TestConfigData.appJson)
                .body(user)
                .when()
                .post("Account/v1/User")
                .then()
                .extract()
                .response();

        String responseBody = response.getBody().asString();
        System.out.println(responseBody);

        JSONObject jsonResponse = new JSONObject(responseBody);
        assertEquals(jsonResponse.getString("books"), "[]");
        assertEquals(jsonResponse.getString("username"), Scenario2Data.RandomUserName);
    }
    @Test (description = "Check that newly added user is not authorized")
    public void createUser2() throws JSONException {
        String user = Scenario2Data.getUser2Json(Scenario2Data.RandomUserName);

        RestAssured.baseURI = TestConfigData.userLink;
        Response response = given()
                .header(TestConfigData.accept, TestConfigData.appJson)
                .header(TestConfigData.contentType, TestConfigData.appJson)
                .body(user)
                .when()
                .post("Account/v1/Authorized")
                .then()
                .extract()
                .response();

        String responseBody = response.getBody().asString();
        System.out.println(responseBody);

        assertTrue(responseBody.contains("false"), "Response body is not false");
    }

    @Test (description =
            "Generate token\n" +
            "Validate status and result response values\n")
    public void createUser3() throws JSONException {
        String user = Scenario2Data.getUser2Json(Scenario2Data.RandomUserName);

        RestAssured.baseURI = TestConfigData.userLink;
        Response response = given()
                .header(TestConfigData.accept, TestConfigData.appJson)
                .header(TestConfigData.contentType, TestConfigData.appJson)
                .body(user)
                .when()
                .post("Account/v1/GenerateToken")
                .then()
                .extract()
                .response();

        String responseBody = response.getBody().asString();
        System.out.println(responseBody);

        JSONObject jsonResponse = new JSONObject(responseBody);

        assertEquals(jsonResponse.getString("status"), "Success");
        assertEquals(jsonResponse.getString("result"), "User authorized successfully.");
    }
    @Test (description = "Check that user became authorized")
    public void createUser4() throws JSONException {
        String user = Scenario2Data.getUser2Json(Scenario2Data.RandomUserName);

        RestAssured.baseURI = TestConfigData.userLink;
        Response response = given()
                .header(TestConfigData.accept, TestConfigData.appJson)
                .header(TestConfigData.contentType, TestConfigData.appJson)
                .body(user)
                .when()
                .post("Account/v1/Authorized")
                .then()
                .extract()
                .response();

        String responseBody = response.getBody().asString();
        System.out.println(responseBody);

        assertTrue(responseBody.contains("true"), "Response body is not true");
    }
}


