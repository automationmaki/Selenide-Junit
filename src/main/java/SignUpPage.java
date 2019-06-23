import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.sun.org.apache.xerces.internal.dom.DeferredEntityReferenceImpl;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static java.lang.String.format;
import static org.openqa.selenium.By.xpath;

public class SignUpPage {

    private By emailField = By.cssSelector("#register-email");
    private By confirmEmailField = By.cssSelector("#register-confirm-email");
    private By passwordField = By.cssSelector("#register-password");
    private By nameField = By.cssSelector("#register-displayname");
    private By monthDropDown = By.cssSelector("#register-dob-month");
    private String monthDropDownOption = "//select[@id='register-dob-month']/option[text()='%s']";
    private By dayField = By.cssSelector("#register-dob-day");
    private By yearField = By.cssSelector("#register-dob-year");
    private String sexRadioButton = "//li[@id='li-gender']//label[normalize-space()='Male']/input";
    private By shareCheckbox = By.cssSelector("#register-thirdparty");
    private By registerButton = By.cssSelector("#register-button-email-submit");
    private By errorLabel = xpath("//label[@class='has-error']");
    private String errorByText = "//label[@class=\"has-error\" and text()=\"%s\"]";

    public SignUpPage open(){
        Selenide.open("/");
        return this;
    }

    public SignUpPage typeEmail(String email){
        $(emailField).setValue(email);
        return this;
    }
    public SignUpPage typeConfirmEmailField(String email){
        $(confirmEmailField).setValue(email);
        return this;
    }

    public SignUpPage typePassword(String password){
        $(passwordField).setValue(password);
        return this;
    }

    public SignUpPage typeName(String name){
        $(nameField).setValue(name);
        return this;
    }

    public SignUpPage setMonth(String month){
        $(monthDropDown).selectOption(month);
        return this;
    }

    public SignUpPage typeDay(String day){
        $(dayField).setValue(day);
        return this;
    }

    public SignUpPage typeYear(String year){
        $(yearField).setValue(year);
        return this;
    }

    public SignUpPage setSex(String value){
        $(By.xpath(String.format(sexRadioButton, value))).click();
        return this;
    }

    public SignUpPage setShare(boolean value){
        $(shareCheckbox).setSelected(value);

        return this;
    }

    public void clickSignUpButton (){
        $(registerButton).waitUntil(Condition.enabled, 5000);
        $(registerButton).click();
    }

    public ElementsCollection getErrors(){
        return $$(errorLabel);
    }

    public String getErrorByNumber(int number){
        return getErrors().get(number - 1).text();
    }

    public SelenideElement getError(String message){
        return $(xpath(format(errorByText, message)));
    }




}
