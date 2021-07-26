package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountPage extends BasePage {
    public AccountPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[contains(text(),'My personal information')]")
    protected WebElement personalInfo;

    @FindBy(xpath = "//*[contains(text(),'My addresses')]")
    protected WebElement addressInfo;

    @FindBy(xpath = "//*[@title='View my customer account']")
    protected WebElement myAcc;

    @FindBy(xpath = "//*[@title='Log me out']")
    protected WebElement logMeOut;

    public void editPersonalInfo() {
        personalInfo.click();
    }

    public void editAddressInfo() {
        addressInfo.click();
    }

    public void viewMyAcc() {
        myAcc.click();
    }

    public void logOut() {
        logMeOut.click();
    }

}
