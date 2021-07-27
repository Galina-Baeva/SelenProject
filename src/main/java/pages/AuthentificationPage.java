package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AuthentificationPage extends BasePage {

    public AuthentificationPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//input[@name='email_create']")
    protected WebElement createAcc;

    @FindBy(xpath = "//input[@name='email']")
    protected WebElement emailAcc;

    @FindBy(xpath = "//input[@name='passwd']")
    protected WebElement passwordAcc;

    @FindBy(xpath = "//*[@class='alert alert-danger']")
    protected WebElement alert;

    public WebElement getEmailAcc() {
        return emailAcc;
    }

    public WebElement getAlert() {
        return alert;
    }

    public WebElement getCreateAcc() {
        return createAcc;
    }

    public void enterEmail(String email) {
        createAcc.sendKeys(String.valueOf(email), Keys.ENTER);
    }

    public void enterCurrentEmail(String email) {
        emailAcc.sendKeys(String.valueOf(email));
    }

    public void enterCurrentPassword(String password) {
        passwordAcc.sendKeys(String.valueOf(password), Keys.ENTER);
    }

    public void logIn (String email, String password){
        enterCurrentEmail(email);
        enterCurrentPassword(password);
    }

}

