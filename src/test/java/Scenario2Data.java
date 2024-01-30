import java.util.Random;

public class Scenario2Data {
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

    public static String RandomUserName = "userName" + randomString(4);

    public static String getUser2Json(String randomUserName) {
        return "{\n" +
                "  \"userName\": \"" + randomUserName + "\",\n" +
                "  \"password\": \"Test!23$%$%$\"\n" +
                "}\n";
    }
}
