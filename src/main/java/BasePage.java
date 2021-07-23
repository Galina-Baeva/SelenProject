import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
    protected final WebDriver driver;
    protected final WebDriverWait wait;

    public BasePage (WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait( driver, 20 );
        PageFactory.initElements(this.driver, this);
    }
}
