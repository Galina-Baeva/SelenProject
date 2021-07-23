import io.github.bonigarcia.wdm.WebDriverManager;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Properties;
import java.util.logging.Logger;

public class Register {
    HomePage homePage;
    AuthentificationPage authentificationPage;
    FormPage formPage;
    AddressInformationPage addressInformationPage;
    PersonalInformationPage personalInformationPage;
    AccountPage accountPage;

    private static final Logger logger = Logger.getLogger((Register.class.getName()));

    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
    Long number = timestamp.getTime();
    String email = number + "@mail.ru";

   // @Parameters({"urlaccountpage"})
    @Test
    public void register() throws IOException, ParseException {
        logger.info("Test starts");
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, 15);
        homePage = new HomePage(driver);
        authentificationPage = new AuthentificationPage(driver);
        formPage = new FormPage(driver);

        Properties property = new Properties();
        property.load(new FileInputStream("config.properties"));

        String url = property.getProperty("url");

        driver.get(url);
        wait.until(ExpectedConditions.visibilityOf(homePage.signIn));

        homePage.clickToSignIn();
        wait.until(ExpectedConditions.visibilityOf(authentificationPage.createAcc));

        authentificationPage.enterEmail(email);
        wait.until(ExpectedConditions.visibilityOf(formPage.formTitle));
        formPage.setPersonalInformation(Data.name, Data.lastName, Data.passwrd,
                Data.date, Data.month, Data.year, Data.address, Data.city, Data.id_state,
                Data.zipCode, Data.country, Data.phoneNumber);
        formPage.submitForm();
        logger.info("Personal information set");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[contains(text(), 'My account')]")));
        String n = driver.getTitle();
        Assert.assertEquals(n, "My account - My Store", "Registration failed");
        logger.info("Created account with an email: " + email);
        driver.close();
        logger.info("Test - success");
    }

    @Test(dependsOnMethods="register")
    public void accountVerification() throws IOException, ParseException {
        logger.info("Test starts");
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, 15);
        authentificationPage = new AuthentificationPage(driver);
        addressInformationPage = new AddressInformationPage(driver);
        personalInformationPage = new PersonalInformationPage(driver);
        accountPage = new AccountPage(driver);

        Properties property = new Properties();
        property.load(new FileInputStream("config.properties"));

        String url = property.getProperty("url");
        String urlaccountpage = property.getProperty("urlaccountpage");
        driver.get(url + urlaccountpage);
        authentificationPage.enterCurrentEmail(email);
        authentificationPage.enterCurrentPassword(Data.passwrd);
        accountPage.editPersonalInfo();
        wait.until(ExpectedConditions.visibilityOf(personalInformationPage.personalTitle));
        String name = personalInformationPage.personalFirstName.getAttribute("value");
        Assert.assertEquals(name, Data.name.toString(), "Name is incorrect");
        String lastName = personalInformationPage.personalLastName.getAttribute("value");
        Assert.assertEquals(lastName, Data.lastName.toString(), "Lastname is incorrect");
        String eMail = personalInformationPage.personalEmail.getAttribute("value");
        Assert.assertEquals(eMail, email, "E-mail is incorrect");
        personalInformationPage.confirmPassword(Data.passwrd);
        personalInformationPage.changePassword(Data.newPasswrd);
        personalInformationPage.saveChanges();
        logger.info("Password changed");
        accountPage.viewMyAcc();
        accountPage.editAddressInfo();
        addressInformationPage.updateAddress();
        String currentAddress = addressInformationPage.addressAddress.getAttribute("value");
        Assert.assertEquals(currentAddress, Data.address, "Address is incorrect");
        addressInformationPage.submitAddressChanges();
        driver.close();
        logger.info("Test - success");
    }
}
