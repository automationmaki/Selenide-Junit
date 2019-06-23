import org.junit.*;
import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Configuration.browser;

public class SignUpTest {
    private SignUpPage page;

    @BeforeClass
    public static void setUp(){
        System.setProperty("webdriver.gecko.driver", "C:\\Users\\admin\\IdeaProjects\\pageobjectseleniumtest\\drivers\\geckodriver.exe");

        baseUrl = "https://www.spotify.com/au/signup";
        browser = "marionette";

    }

    @Test
    public void typeInvalidYear(){
        page = new SignUpPage();
        page.open()
                .setMonth("December")
                .typeDay("20")
                .typeYear("85")
                .setShare(true);
        page.getError("Please enter a valid year.").shouldBe(visible);
        page.getError("When were you born?").shouldNotBe(visible);

    }

    @Test
    public void typeInvalidEmail(){
        page = new SignUpPage();
        page.open()
                .typeName("test@mail.test")
                .typeConfirmEmailField("wrong@mail.test")
                .typeName("Testname")
                .clickSignUpButton();
        page.getError("Email address doesn't match.").shouldBe(visible);
    }

    @Test
    public void signUpWithEmptyPassword(){
        page = new SignUpPage();
        page.open()
                .typeEmail("test@mail.test")
                .typeConfirmEmailField("test@mail.test")
                .typeName("Testname")
                .clickSignUpButton();
        page.getError("Please choose a password.").shouldBe(visible);

    }

    @Test
    public void typeInvalidValues(){
        page = new SignUpPage();
        page.open()
                .typeEmail("testmail")
                .typeConfirmEmailField("wrong@test.mail")
                .typePassword("qweqwe!")
                .typeName("Name")
                .setSex("Male")
                .setShare(false)
                .clickSignUpButton();
        page.getErrors().shouldHave(size(9));
//        Assert.assertEquals("When were you born", page.getErrorByNumber(3));
    }

//    @After
//    public void tearDown(){
//        driver.quit();
//    }
}
