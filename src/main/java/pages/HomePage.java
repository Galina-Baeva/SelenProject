package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(className = "login")
    protected WebElement signIn;

    public WebElement getSignIn() {
        return signIn;
    }

    public void clickToSignIn() {
        signIn.click();
    }
}
