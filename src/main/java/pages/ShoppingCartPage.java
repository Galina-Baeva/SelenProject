package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ShoppingCartPage extends BasePage {
    public ShoppingCartPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//a[@title='Proceed to checkout']//span")
    protected WebElement checkOut;

    @FindBy(xpath = "//span[contains(text(), 'Proceed to checkout')]")
    protected WebElement processAddress;

    @FindBy(xpath = "//input[@type='checkbox']")
    protected WebElement checkbox;

    @FindBy(xpath = "//button[@name='processCarrier']//span")
    protected WebElement processCarrier;

    @FindBy(xpath = "//a[@class='bankwire']")
    protected WebElement byBank;

    @FindBy(xpath = "//span[contains(text(), 'I confirm my order')]")
    protected WebElement confirmButton;

    @FindBy(xpath = "//a[@title='My orders']")
    protected WebElement myOrdersButton;

    public WebElement getCheckOut() {
        return checkOut;
    }

    public void proceedToCheckout() {
        checkOut.click();
        processAddress.click();
        checkbox.click();
        processCarrier.click();
        byBank.click();
        confirmButton.click();
    }

    public void goToOrders() {
        myOrdersButton.click();
    }

}
