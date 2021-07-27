package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ShoppingCartPage extends BasePage {
    public ShoppingCartPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//a[@class='button btn btn-default standard-checkout button-medium']")
    protected WebElement checkOut;

    @FindBy(xpath = "//button[@name = 'processAddress']")
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
        wait.until(ExpectedConditions.visibilityOf(processAddress));
        processAddress.click();
        wait.until(ExpectedConditions.visibilityOf(processCarrier));
        checkbox.click();
        processCarrier.click();
        wait.until(ExpectedConditions.visibilityOf(byBank));
        byBank.click();
        wait.until(ExpectedConditions.visibilityOf(confirmButton));
        confirmButton.click();
    }

    public void goToOrders() {
        myOrdersButton.click();
    }

}
