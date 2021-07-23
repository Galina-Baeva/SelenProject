import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class PersonalInformationPage extends BasePage {
    public PersonalInformationPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//div[@id='uniform-id_gender1']")
    protected WebElement personalTitle;

    @FindBy(xpath = "//input[@name='firstname']")
    protected WebElement personalFirstName;

    @FindBy(xpath = "//input[@name='lastname']")
    protected WebElement personalLastName;

    @FindBy(xpath = "//input[@name='email']")
    protected WebElement personalEmail;

    @FindBy(xpath = "//input[@name='old_passwd']")
    protected WebElement currentPassword;

    @FindBy(xpath = "//input[@name='passwd']")
    protected WebElement newPassword;

    @FindBy(xpath = "//input[@name='confirmation']")
    protected WebElement confirmPassword;

    @FindBy(xpath = "//*[@name='days']")
    protected WebElement personalDate;

    @FindBy(xpath = "//*[@name='months']")
    protected WebElement personalMonth;

    @FindBy(xpath = "//select[@name='years']")
    protected WebElement personalYear;

    @FindBy(xpath = "//button[@name='submitIdentity']")
    protected WebElement personalSave;

    public void changeTitle() {
        personalTitle.click();
    }

    public void changeFirstName(String name) {
        personalFirstName.sendKeys(name);
    }

    public void changeLastName(String lastName) {
        personalLastName.sendKeys(lastName);
    }

    public void confirmPassword(String passwrd) {
        currentPassword.sendKeys(passwrd);
    }

    public void changePassword(String passwrd) {
        newPassword.sendKeys(passwrd);
        confirmPassword.sendKeys(passwrd);
    }

    public void changeDate(String date) {
        Select dropdownMenu = new Select(personalDate);
        dropdownMenu.selectByValue(date);
    }

    public void changeMonth(String month) {
        Select dropdownMenu = new Select(personalMonth);
        dropdownMenu.selectByValue(month);
    }

    public void changeYear(String year) {
        Select dropdownMenu = new Select(personalYear);
        dropdownMenu.selectByValue(year);
    }
    public void saveChanges() {
        personalSave.click();
    }
}
