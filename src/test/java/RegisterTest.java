import io.github.bonigarcia.wdm.WebDriverManager;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
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
    FormPage formPage;
    AddressInformationPage addressInformationPage;
    PersonalInformationPage personalInformationPage;
    AccountPage accountPage;
    DataReader data;

    private static final Logger logger = Logger.getLogger((RegisterTest.class.getName()));

    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
    Long number = timestamp.getTime();
    String email = number + "@mail.ru";

    @Parameters({"path"})
    @Test
    public void register(String path) throws IOException, ParseException {
        logger.info("Test starts");
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, 15);
        homePage = new HomePage(driver);
        authentificationPage = new AuthentificationPage(driver);
        formPage = new FormPage(driver);
        addressInformationPage = new AddressInformationPage(driver);
        personalInformationPage = new PersonalInformationPage(driver);
        accountPage = new AccountPage(driver);
        data = new DataReader(path);

        Properties property = new Properties();
        property.load(new FileInputStream("config.properties"));

        String url = property.getProperty("url");

        driver.get(url);
        wait.until(ExpectedConditions.visibilityOf(homePage.getSignIn()));

        homePage.clickToSignIn();
        wait.until(ExpectedConditions.visibilityOf(authentificationPage.getCreateAcc()));

        authentificationPage.enterEmail(email);
        wait.until(ExpectedConditions.visibilityOf(formPage.getFormTitle()));
        formPage.setPersonalInformation(data.name, data.lastName, data.passwrd,
                data.date, data.month, data.year, data.address, data.city, data.id_state,
                data.zipCode, data.country, data.phoneNumber);
        formPage.submitForm();
        logger.info("Personal information set");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[contains(text(), 'My account')]")));
        String n = driver.getTitle();
        Assert.assertEquals(n, "My account - My Store", "Registration failed");
        logger.info("Created account with an email: " + email);
        driver.close();
        logger.info("Test - success");
    }

    @Test(dependsOnMethods = "register")
    public void accountVerification() throws IOException, ParseException {
        logger.info("Test starts");
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, 15);
        homePage = new HomePage(driver);
        authentificationPage = new AuthentificationPage(driver);
        formPage = new FormPage(driver);
        addressInformationPage = new AddressInformationPage(driver);
        personalInformationPage = new PersonalInformationPage(driver);
        accountPage = new AccountPage(driver);

        Properties property = new Properties();
        property.load(new FileInputStream("config.properties"));

        String url = property.getProperty("url");
        String urlaccountpage = property.getProperty("urlaccountpage");
        driver.get(url + urlaccountpage);
        authentificationPage.enterCurrentEmail(email);
        authentificationPage.enterCurrentPassword(DataReader.passwrd);
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
        driver.close();
        logger.info("Test - success");
    }
}
