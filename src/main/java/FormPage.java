import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FormPage {
    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(xpath = "//div[@id='uniform-id_gender1']")
    private WebElement formTitle;

    @FindBy(xpath = "//input[@name='customer_firstname']")
    private WebElement formFirstName;

    @FindBy(xpath = "//input[@name='customer_lastname']")
    private WebElement formLastName;

    @FindBy(xpath = "//input[@name='passwd']")
    private WebElement formPassword;

    @FindBy(xpath = "//select[@name='days']//option[@value='12']")
    private WebElement formDate;

    @FindBy(xpath = "//select[@name='months']//option[@value='3']")
    private WebElement formMonth;

    @FindBy(xpath = "//select[@name='years']//option[@value='1990']")
    private WebElement formYear;

    @FindBy(xpath = "//input[@name='address1']")
    private WebElement addressAddress;

    @FindBy(xpath = "//input[@name='city']")
    private WebElement addressCity;

    @FindBy(xpath = "//select[@name='id_state']//option[@value='32']")
    private WebElement addressState;

    @FindBy(xpath = "//input[@name='postcode']")
    private WebElement addressZip;

    @FindBy(xpath = "//select[@id='id_country']//option[@value='21']")
    private WebElement addressCountry;

    @FindBy(xpath = "//input[@name='phone_mobile']")
    private WebElement addressPhone;

    @FindBy(xpath = "//button[@name='submitAccount']")
    private WebElement formRegister;

    public FormPage (WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait( driver, 20 );
        PageFactory.initElements(driver, this);
    }
    public void chooseTitle() {
        formTitle.click();
    }
    public void enterFirstName(String name) {
        formFirstName.sendKeys(name);
    }
    public void enterLastName(String lastName) {
        formLastName.sendKeys(lastName);
    }
    public void enterPassword(String passwrd) {
        formPassword.sendKeys(passwrd);
    }
    public void selectDate() {
        formDate.click();
    }
    public void selectMonth() {
        formMonth.click();
    }
    public void selectYear() {
        formYear.click();
    }
    public void enterAddress(String address) {
        addressAddress.sendKeys(address);
    }
    public void enterCity(String city) {
        addressCity.sendKeys(city);
    }
    public void selectState() {
        addressState.click();
    }
    public void enterZip(String zipCode) {
        addressZip.sendKeys(zipCode);
    }
    public void selectCountry() {
        addressCountry.click();
    }
    public void enterPhoneNumber(String phoneNumber) {
        addressPhone.sendKeys(phoneNumber);
    }
    public void submitForm() {
        formRegister.click();
    }
}