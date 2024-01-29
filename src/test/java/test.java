import com.codeborne.selenide.Selectors;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.$;

public class test extends check{
    @Test
    public void gfdsgdf(){
        $(Selectors.byText("შესვლა")).click();
    }
}
