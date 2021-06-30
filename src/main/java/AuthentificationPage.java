import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.sql.Timestamp;


public class AuthentificationPage {
    private WebDriver driver;
    private WebDriverWait wait;
    By createAcc = By.xpath("//input[@name='email_create']");

    public AuthentificationPage (WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait( driver, 20 );
    }

    public void enterEmail(String email) {
        driver.findElement(createAcc).sendKeys(String.valueOf(email), Keys.ENTER);
    }
}

