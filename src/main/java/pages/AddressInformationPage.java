package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class AddressInformationPage extends BasePage {

    public AddressInformationPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//span[contains(text(),'Update')]")
    protected WebElement updateButton;

    @FindBy(xpath = "//input[@name='address1']")
    protected WebElement addressAddress;

    @FindBy(xpath = "//input[@name='city']")
    protected WebElement addressCity;

    @FindBy(xpath = "//*[@id='id_state']")
    protected WebElement addressState;

    @FindBy(xpath = "//input[@name='postcode']")
    protected WebElement addressZip;

    @FindBy(xpath = "//*[@id='id_country']")
    protected WebElement addressCountry;

    @FindBy(xpath = "//input[@name='phone_mobile']")
    protected WebElement addressPhone;

    @FindBy(xpath = "//button[@name='submitAddress']")
    protected WebElement submitAddress;

    public WebElement getAddressAddress() {
        return addressAddress;
    }

    public void updateAddress() {
        updateButton.click();
    }

    public void enterAddress(String address) {
        addressAddress.sendKeys(address);
    }

    public void enterCity(String city) {
        addressCity.sendKeys(city);
    }

    public void selectState(String id_state) {
        Select dropdownMenu = new Select(addressState);
        dropdownMenu.selectByVisibleText(id_state);
        addressState.click();
    }

    public void enterZip(String zipCode) {
        addressZip.sendKeys(zipCode);
    }

    public void selectCountry(String country) {
        Select dropdownMenu = new Select(addressCountry);
        dropdownMenu.selectByVisibleText(country);
        addressCountry.click();
    }

    public void enterPhoneNumber(String phoneNumber) {
        addressPhone.sendKeys(phoneNumber);
    }

    public void submitAddressChanges() {
        submitAddress.click();
    }
}