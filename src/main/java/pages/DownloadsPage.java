package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DownloadsPage extends BasePage {
    public DownloadsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "body")
    protected WebElement downloads;

    public WebElement getDownloads() {
        return downloads;
    }
}
