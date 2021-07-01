import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AuthentificationPage {
    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(xpath = "//input[@name='email_create']")
    private WebElement createAcc;

    public AuthentificationPage (WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait( driver, 20 );
        PageFactory.initElements(driver, this);
    }

    public void enterEmail(String email) {
        createAcc.sendKeys(String.valueOf(email), Keys.ENTER);
    }
}

