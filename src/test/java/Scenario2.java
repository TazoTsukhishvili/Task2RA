import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.annotations.Test;

import java.util.Random;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.*;

public class Scenario2 {
    String userLink = "https://bookstore.toolsqa.com";
    String accept = "accept";
    String contentType = "Content-Type";
    String appJson = "application/json";

    public static String randomString(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder(length);
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            sb.append(characters.charAt(index));
        }
        return sb.toString();
    }

    String userName = "userName" + randomString(4);

    @Test
    public void createUser1() throws JSONException {
        String user = "{\n" +
                "  \"userName\": \"" + userName + "\",\n" +
                "  \"password\": \"Test!23$%$%$\"\n" +
                "}\n";
        RestAssured.baseURI = userLink;
        Response response = given()
                .header(accept, appJson)
                .header(contentType, appJson)
                .body(user)
                .when()
                .post("Account/v1/User")
                .then()
                .extract()
                .response();
        //   System.out.println(response.body().asString());

        String responseBody = response.getBody().asString();
        System.out.println(responseBody);

        JSONObject jsonResponse = new JSONObject(responseBody);
        //assertEquals(jsonResponse.getString("code"), "1204");
        //assertEquals(jsonResponse.getString("message"), "User exists!");
        assertEquals(jsonResponse.getString("books"), "[]");
        assertEquals(jsonResponse.getString("username"), userName);


    }

    @Test
    public void createUser2() throws JSONException {
        String user = "{\n" +
                "  \"userName\": \"" + userName + "\",\n" +
                "  \"password\": \"Test!23$%$%$\"\n" +
                "}\n";
        RestAssured.baseURI = userLink;
        Response response = given()
                .header(accept, appJson)
                .header(contentType, appJson)
                .body(user)
                .when()
                .post("Account/v1/Authorized")
                .then()
                .extract()
                .response();
        //   System.out.println(response.body().asString());

        String responseBody = response.getBody().asString();
        System.out.println(responseBody);


        assertTrue(responseBody.contains("false"), "Response body is not false");

    }

    @Test
    public void createUser3() throws JSONException {
        String user = "{\n" +
                "  \"userName\": \"" + userName + "\",\n" +
                "  \"password\": \"Test!23$%$%$\"\n" +
                "}\n";
        RestAssured.baseURI = userLink;
        Response response = given()
                .header(accept, appJson)
                .header(contentType, appJson)
                .body(user)
                .when()
                .post("Account/v1/GenerateToken")
                .then()
                .extract()
                .response();
        //   System.out.println(response.body().asString());

        String responseBody = response.getBody().asString();
        System.out.println(responseBody);

        JSONObject jsonResponse = new JSONObject(responseBody);

        assertEquals(jsonResponse.getString("status"), "Success");
        assertEquals(jsonResponse.getString("result"), "User authorized successfully.");



    }


   // createUser4 BUG

    @Test
    public void createUser4() throws JSONException {
        String user = "{\n" +
                "  \"userName\": \"" + userName + "\",\n" +
                "  \"password\": \"Test!23$%$%$\"\n" +
                "}\n";
        RestAssured.baseURI = userLink;
        Response response = given()
                .header(accept, appJson)
                .header(contentType, appJson)
                .body(user)
                .when()
                .post("Account/v1/Authorized")
                .then()
                .extract()
                .response();
        //   System.out.println(response.body().asString());

        String responseBody = response.getBody().asString();
        System.out.println(responseBody);

    //    JSONObject jsonResponse = new JSONObject(responseBody);

        assertTrue(responseBody.contains("true"), "Response body is not true");


    }


}


