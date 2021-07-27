import io.github.bonigarcia.wdm.WebDriverManager;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.*;
import testData.DataReader;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Properties;
import java.util.logging.Logger;

public class NegativeAccountRegistration {
    HomePage homePage;
    AuthentificationPage authentificationPage;
    RegistrationFormPage registrationFormPage;
    DataReader data;
    WebDriver driver;
    WebDriverWait wait;
    Timestamp timestamp;
    Long number;
    String email;
    String invalidEmail;
    FileInputStream fileInputStream;
    Properties property;
    String url;

    private static final Logger logger = Logger.getLogger(RegisterTest.class.getName());

    @Parameters({"prop"})
    @BeforeClass
    public void setUp(String prop) throws IOException {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 7);
        timestamp = new Timestamp(System.currentTimeMillis());
        number = timestamp.getTime();
        email = number + "@mail.ru";
        invalidEmail = null;
        property = new Properties();
        fileInputStream = new FileInputStream(prop);
        property.load(fileInputStream);
        url = property.getProperty("url");
    }

    @AfterClass
    public void closeDriver() {
        driver.close();
    }

    @Parameters({"path"})
    @Test
    public void registerEmptyRequiredFields(String path) throws IOException, ParseException {
        logger.info("Test starts");
        homePage = new HomePage(driver);
        authentificationPage = new AuthentificationPage(driver);
        registrationFormPage = new RegistrationFormPage(driver);
        data = new DataReader(path);

        driver.get(url);
        wait.until(ExpectedConditions.visibilityOf(homePage.getSignIn()));
        homePage.clickToSignIn();
        wait.until(ExpectedConditions.visibilityOf(authentificationPage.getCreateAcc()));
        authentificationPage.enterEmail(email);
        wait.until(ExpectedConditions.visibilityOf(registrationFormPage.getFormTitle()));
        registrationFormPage.setPartOfPersonalInfo(data.name, data.lastName, data.passwrd);
        registrationFormPage.submitForm();
        logger.info("Personal information set");
        wait.until(ExpectedConditions.visibilityOf(registrationFormPage.getAlert()));
        logger.info("There is an error, User must fill all required fields");
        logger.info("Test - success");
    }

    @Test
    public void registerWithEmptyEmail() {
        logger.info("Test starts");
        homePage = new HomePage(driver);
        authentificationPage = new AuthentificationPage(driver);
        driver.get(url);
        wait.until(ExpectedConditions.visibilityOf(homePage.getSignIn()));
        homePage.clickToSignIn();
        wait.until(ExpectedConditions.visibilityOf(authentificationPage.getCreateAcc()));
        authentificationPage.enterEmail(invalidEmail);
        wait.until(ExpectedConditions.visibilityOf(authentificationPage.getAlert()));
        String alertMessage = authentificationPage.getAlert().getText();
        Assert.assertEquals(alertMessage, "Invalid email address.");
        logger.info("There is a message 'Invalid email address.'");
        logger.info("Test - success");
    }
}
