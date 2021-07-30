import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Properties;
import java.util.logging.Logger;

public class PageElementsVerification {
    HomePage homePage;
    AuthentificationPage authentificationPage;
    RegistrationFormPage registrationFormPage;
    WebDriver driver;
    WebDriverWait wait;
    Timestamp timestamp;
    Long number;
    String email;
    FileInputStream fileInputStream;
    Properties property;
    String url;

    private static final Logger logger = Logger.getLogger(RegisterTest.class.getName());

    @Parameters({"prop"})
    @BeforeClass
    public void setUp(String prop) throws IOException {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
        timestamp = new Timestamp(System.currentTimeMillis());
        number = timestamp.getTime();
        email = number + "@mail.ru";
        property = new Properties();
        fileInputStream = new FileInputStream(prop);
        property.load(fileInputStream);
        url = property.getProperty("url");
    }

    @AfterClass
    public void closeDriver() {
        driver.close();
    }

    @Test
    public void register() {
        logger.info("Test starts");
        homePage = new HomePage(driver);
        authentificationPage = new AuthentificationPage(driver);
        registrationFormPage = new RegistrationFormPage(driver);

        driver.get(url);
        wait.until(ExpectedConditions.visibilityOf(homePage.getSignIn()));
        homePage.clickToSignIn();
        wait.until(ExpectedConditions.visibilityOf(authentificationPage.getCreateAcc()));
        authentificationPage.enterEmail(email);
        wait.until(ExpectedConditions.visibilityOf(registrationFormPage.getFormTitle()));
        boolean elementExist = registrationFormPage.existsElement();
        Assert.assertTrue(elementExist, "Not all elements are exist");
        logger.info("All elements are exist and displayed");
        String pageHeader = registrationFormPage.getPageHeader().getText();
        Assert.assertEquals(pageHeader, "CREATE AN ACCOUNT", "Invalid name of the page title");
        String pageSubHeader = registrationFormPage.getPageSubHeader().getText();
        Assert.assertEquals(pageSubHeader, "YOUR PERSONAL INFORMATION", "Invalid name of the page subtitle");
        String registerButton = registrationFormPage.getFormRegister().getText();
        Assert.assertEquals(registerButton, "Register", "Invalid name of the 'Register' button");
        logger.info("Elements are named correctly");
        logger.info("Test - success");
    }
}
