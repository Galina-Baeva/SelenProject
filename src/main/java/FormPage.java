import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FormPage {
    private WebDriver driver;
    private WebDriverWait wait;
    By formTitle = By.xpath("//div[@id='uniform-id_gender1']");
    By formFirstName = By.xpath("//input[@name='customer_firstname']");
    By formLastName = By.xpath("//input[@name='customer_lastname']");
    By formPassword = By.xpath("//input[@name='passwd']");
    By formDate = By.xpath("//select[@name='days']//option[@value='12']");
    By formMonth = By.xpath("//select[@name='months']//option[@value='3']");
    By formYear = By.xpath("//select[@name='years']//option[@value='1990']");
    By addressAddress = By.xpath("//input[@name='address1']");
    By addressCity = By.xpath("//input[@name='city']");
    By addressState = By.xpath("//select[@name='id_state']//option[@value='32']");
    By addressZip = By.xpath("//input[@name='postcode']");
    By addressCountry = By.xpath("//select[@id='id_country']//option[@value='21']");
    By addressPhone = By.xpath("//input[@name='phone_mobile']");
    By formRegister = By.xpath("//button[@name='submitAccount']");


    public FormPage (WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait( driver, 20 );
    }
    public void chooseTitle() {
        driver.findElement(formTitle).click();
    }
    public void enterFirstName() {
        driver.findElement(formFirstName).sendKeys("Ivan");
    }
    public void enterLastName() {
        driver.findElement(formLastName).sendKeys("Ivanov");
    }
    public void enterPassword() {
        driver.findElement(formPassword).sendKeys("12345");
    }
    public void selectDate() {
        driver.findElement(formDate).click();
    }
    public void selectMonth() {
        driver.findElement(formMonth).click();
    }
    public void selectYear() {
        driver.findElement(formYear).click();
    }
    public void enterAddress() {
        driver.findElement(addressAddress).sendKeys("Fifth street 5");
    }
    public void enterCity() {
        driver.findElement(addressCity).sendKeys("Gorod");
    }
    public void selectState() {
        driver.findElement(addressState).click();
    }
    public void enterZip() {
        driver.findElement(addressZip).sendKeys("12345");
    }
    public void selectCountry() {
        driver.findElement(addressCountry).click();
    }
    public void enterPhoneNumber() {
        driver.findElement(addressPhone).sendKeys("3059029325");
    }
    public void submitForm() {
        driver.findElement(formRegister).click();
    }
}