package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SearchPage extends BasePage {
    public SearchPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//span[contains(text(), 'Add to cart')]")
    protected WebElement firstFoundDress;

    @FindBy(xpath = "//a[@title='Proceed to checkout']")
    protected WebElement checkOut;

    public void chooseFirstFoundDress() {
        firstFoundDress.click();
    }

    public void proceedToCheckout() {
        checkOut.click();
    }

    public void addToCart() {
        chooseFirstFoundDress();
        wait.until(ExpectedConditions.visibilityOf(checkOut));
        proceedToCheckout();
    }

}
