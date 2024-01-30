import org.testng.annotations.DataProvider;

public class UserDataProvider {
    @DataProvider(name = "userData")
    public Object[][] userData() {
        return new Object[][]{
                {"automation1", "Automation@!@123", "1204", "User exists!"},
                {"automation2", "Auto@2", "1300", "Passwords must have at least one non alphanumeric character, one digit ('0'-'9'), one uppercase ('A'-'Z'), one lowercase ('a'-'z'), one special character and Password must be eight characters or longer."},
                {"automation3", "", "1200", "UserName and Password required."}
        };
    }
}
