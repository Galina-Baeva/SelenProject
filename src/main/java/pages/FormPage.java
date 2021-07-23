package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;


public class FormPage extends BasePage {
    public FormPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//div[@id='uniform-id_gender1']")
    protected WebElement formTitle;

    @FindBy(xpath = "//input[@name='customer_firstname']")
    protected WebElement formFirstName;

    @FindBy(xpath = "//input[@name='customer_lastname']")
    protected WebElement formLastName;

    @FindBy(xpath = "//input[@name='passwd']")
    protected WebElement formPassword;

    @FindBy(xpath = "//*[@name='days']")
    protected WebElement formDate;

    @FindBy(xpath = "//*[@name='months']")
    protected WebElement formMonth;

    @FindBy(xpath = "//select[@name='years']")
    protected WebElement formYear;

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

    @FindBy(xpath = "//button[@name='submitAccount']")
    protected WebElement formRegister;

    public WebElement getFormTitle() {
        return formTitle;
    }

    public void setPersonalInformation(String name, String lastName, String passwrd,
                                       String date, String month, String year, String address,
                                       String city, String id_state, String zipCode,
                                       String country, String phoneNumber) {
        formTitle.click();
        formFirstName.sendKeys(name);
        formLastName.sendKeys(lastName);
        formPassword.sendKeys(passwrd);
        Select dropdownMenuDate = new Select(formDate);
        dropdownMenuDate.selectByValue(date);
        Select dropdownMenuMonth = new Select(formMonth);
        dropdownMenuMonth.selectByValue(month);
        Select dropdownMenuYear = new Select(formYear);
        dropdownMenuYear.selectByValue(year);
        addressAddress.sendKeys(address);
        addressCity.sendKeys(city);
        Select dropdownMenuState = new Select(addressState);
        dropdownMenuState.selectByVisibleText(id_state);
        addressState.click();
        addressZip.sendKeys(zipCode);
        Select dropdownMenuCountry = new Select(addressCountry);
        dropdownMenuCountry.selectByVisibleText(country);
        addressCountry.click();
        addressPhone.sendKeys(phoneNumber);
    }

    public void submitForm() {
        formRegister.click();
    }
}
