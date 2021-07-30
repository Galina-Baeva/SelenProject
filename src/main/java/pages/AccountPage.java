package pages;

import org.openqa.selenium.Keys;
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

    @FindBy(xpath = "//input[@name='search_query']")
    protected WebElement searchField;

    @FindBy(xpath = "//table[@id='order-list']")
    protected WebElement orderTable;

    @FindBy(xpath = "//*[@class='footable-toggle']")
    protected WebElement orderDetails;

    @FindBy(xpath = "//td[not(contains(@style,'display: none;'))]//a[@Title='Invoice']")
    protected WebElement invoicePDF;

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

    public void searchItem(String item) {
        searchField.sendKeys(item, Keys.ENTER);
    }

    public boolean findOrderTable() {
        return orderTable.isDisplayed();
    }

    public void seeOrderDetails() {
        orderDetails.click();
    }

    public void getInvoice() {
        invoicePDF.click();
    }

    public WebElement getInvoicePDF() {
        return invoicePDF;
    }
}
