import org.json.JSONException;
import org.json.JSONObject;
import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class Scenario1 {

    String userLink = "https://bookstore.toolsqa.com";
    String accept = "accept";
    String contentType = "Content-Type";
    String appJson = "application/json";

    @Test
    public void createUser1() throws JSONException {
        String user = "{\n" +
                "  \"userName\": \"automation\",\n" +
                "  \"password\": \"Automation@!@123\"\n" +
                "}\n";

        RestAssured.baseURI = userLink;
        Response response = given()
                .header(accept,appJson)
                .header(contentType,appJson)
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
        assertEquals(jsonResponse.getString("code"), "1204");
        assertEquals(jsonResponse.getString("message"), "User exists!");

    }
    @Test
    public void createUser2() throws JSONException {
        String user = "{\n" +
                "  \"userName\": \"automation\",\n" +
                "  \"password\": \"Auto@2\"\n" +
                "}\n";

        RestAssured.baseURI = userLink;
        Response response = given()
                .header(accept,appJson)
                .header(contentType,appJson)
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
        assertEquals(jsonResponse.getString("code"), "1300");
        assertEquals(jsonResponse.getString("message"), "Passwords must have at least one non alphanumeric character, one digit ('0'-'9'), one uppercase ('A'-'Z'), one lowercase ('a'-'z'), one special character and Password must be eight characters or longer.");

    }
    @Test
    public void createUser3() throws JSONException {
        String user = "{\n" +
                "  \"userName\": \"automation\",\n" +
                "  \"password\": \"\"\n" +
                "}\n";

        RestAssured.baseURI = userLink;
        Response response = given()
                .header(accept,appJson)
                .header(contentType,appJson)
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
        assertEquals(jsonResponse.getString("code"), "1200");
        assertEquals(jsonResponse.getString("message"), "UserName and Password required.");

    }

}
