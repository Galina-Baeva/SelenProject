import io.github.bonigarcia.wdm.WebDriverManager;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.*;
import testData.DataReader;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Properties;
import java.util.logging.Logger;

public class RegisterTest {
    HomePage homePage;
    AuthentificationPage authentificationPage;
    RegistrationFormPage registrationFormPage;
    AddressInformationPage addressInformationPage;
    PersonalInformationPage personalInformationPage;
    AccountPage accountPage;
    DataReader data;
    WebDriver driver;
    WebDriverWait wait;
    Timestamp timestamp;
    Long number;
    public static String email;
    FileInputStream fileInputStream;
    Properties property;
    String url;
    String urlAccountPage;

    private static final Logger logger = Logger.getLogger(RegisterTest.class.getName());

    @Parameters({"prop"})
    @BeforeClass
    public void setUp(String prop) throws IOException {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 15);
        timestamp = new Timestamp(System.currentTimeMillis());
        number = timestamp.getTime();
        email = number + "@mail.ru";
        property = new Properties();
        fileInputStream = new FileInputStream(prop);
        property.load(fileInputStream);
        url = property.getProperty("url");
        urlAccountPage = property.getProperty("urlaccountpage");
    }

    @AfterClass
    public void closeDriver() {
        driver.close();
    }

    @AfterMethod
    public void logout() {
        accountPage.logOut();
    }

    @Parameters({"path"})
    @Test
    public void register(String path) throws IOException, ParseException {
        logger.info("Test starts");
        homePage = new HomePage(driver);
        authentificationPage = new AuthentificationPage(driver);
        registrationFormPage = new RegistrationFormPage(driver);
        addressInformationPage = new AddressInformationPage(driver);
        personalInformationPage = new PersonalInformationPage(driver);
        accountPage = new AccountPage(driver);
        data = new DataReader(path);

        driver.get(url);
        wait.until(ExpectedConditions.visibilityOf(homePage.getSignIn()));

        homePage.clickToSignIn();
        wait.until(ExpectedConditions.visibilityOf(authentificationPage.getCreateAcc()));

        authentificationPage.enterEmail(email);
        wait.until(ExpectedConditions.visibilityOf(registrationFormPage.getFormTitle()));
        registrationFormPage.setPersonalInformation(data.name, data.lastName, data.passwrd,
                data.date, data.month, data.year, data.address, data.city, data.id_state,
                data.zipCode, data.country, data.phoneNumber);
        logger.info("Personal information set");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[contains(text(), 'My account')]")));
        String n = driver.getTitle();
        Assert.assertEquals(n, "My account - My Store", "Registration failed");
        logger.info("Created account with an email: " + email);
        logger.info("Test - success");
    }

    @Test(dependsOnMethods = {"register"})
    public void accountVerification() {
        logger.info("Test starts");
        homePage = new HomePage(driver);
        authentificationPage = new AuthentificationPage(driver);
        registrationFormPage = new RegistrationFormPage(driver);
        addressInformationPage = new AddressInformationPage(driver);
        personalInformationPage = new PersonalInformationPage(driver);
        accountPage = new AccountPage(driver);

        driver.get(url + urlAccountPage);
        wait.until(ExpectedConditions.visibilityOf(authentificationPage.getEmailAcc()));
        authentificationPage.logIn(email, DataReader.passwrd);
        accountPage.editPersonalInfo();
        wait.until(ExpectedConditions.visibilityOf(personalInformationPage.getPersonalTitle()));
        String name = personalInformationPage.getPersonalFirstName().getAttribute("value");
        Assert.assertEquals(name, DataReader.name.toString(), "Name is incorrect");
        String lastName = personalInformationPage.getPersonalLastName().getAttribute("value");
        Assert.assertEquals(lastName, DataReader.lastName.toString(), "Lastname is incorrect");
        String eMail = personalInformationPage.getPersonalEmail().getAttribute("value");
        Assert.assertEquals(eMail, email, "E-mail is incorrect");
        personalInformationPage.confirmPassword(DataReader.passwrd);
        personalInformationPage.changePassword(DataReader.newPasswrd);
        personalInformationPage.saveChanges();
        logger.info("Password changed");
        accountPage.viewMyAcc();
        accountPage.editAddressInfo();
        addressInformationPage.updateAddress();
        String currentAddress = addressInformationPage.getAddressAddress().getAttribute("value");
        Assert.assertEquals(currentAddress, DataReader.address, "Address is incorrect");
        addressInformationPage.submitAddressChanges();
        logger.info("Test - success");
    }
}