public class Scenario1Data {
    public static String postValue = "Account/v1/User";
    public static String getUser1Json(String userName, String password) {
        return "{\n" +
                "  \"userName\": \"" + userName + "\",\n" +
                "  \"password\": \"" + password + "\"\n" +
                "}\n";
    }
}
