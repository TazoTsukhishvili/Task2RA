import org.json.JSONException;
import org.json.JSONObject;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class Scenario1 {
    @Test(dataProvider = "userData", dataProviderClass = UserDataProvider.class,
            description = "Validate response CODE and MSG " +
                    "when userName and password: " +
                    "1 - valid, 2 - invalid, 3 - empty")
    public void createUser1(String userName, String password, String expectedCode, String expectedMessage) throws JSONException {
        String user = Scenario1Data.getUser1Json(userName, password);

        RestAssured.baseURI = TestConfigData.userLink;
        Response response = given()
                .header(TestConfigData.accept,TestConfigData.appJson)
                .header(TestConfigData.contentType,TestConfigData.appJson)
                .body(user)
                .when()
                .post(Scenario1Data.postValue)
                .then()
                .extract()
                .response();

        String responseBody = response.getBody().asString();
        System.out.println(responseBody);

        JSONObject jsonResponse = new JSONObject(responseBody);
        assertEquals(jsonResponse.getString("code"), expectedCode);
        assertEquals(jsonResponse.getString("message"), expectedMessage);

    }
}
