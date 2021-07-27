import io.github.bonigarcia.wdm.WebDriverManager;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.*;

import java.sql.Timestamp;

import testData.DataReader;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;

public class PlacingAnOrder {
    AuthentificationPage authentificationPage;
    AccountPage accountPage;
    SearchPage searchPage;
    ShoppingCartPage shoppingCartPage;
    RegistrationFormPage registrationFormPage;
    DataReader data;
    WebDriver driver;
    WebDriverWait wait;
    Timestamp timestamp;
    Long number;
    String email;
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

    @Parameters({"path"})
    @Test
    public void placingAnOrder(String path) throws IOException, ParseException {
        logger.info("Test starts");
        authentificationPage = new AuthentificationPage(driver);
        accountPage = new AccountPage(driver);
        searchPage = new SearchPage(driver);
        shoppingCartPage = new ShoppingCartPage(driver);
        registrationFormPage = new RegistrationFormPage(driver);
        data = new DataReader(path);
        driver.get(url + urlAccountPage);
        wait.until(ExpectedConditions.visibilityOf(authentificationPage.getEmailAcc()));
        authentificationPage.enterEmail(email);
        wait.until(ExpectedConditions.visibilityOf(registrationFormPage.getFormTitle()));
        registrationFormPage.setPersonalInformation(data.name, data.lastName, data.passwrd,
                data.date, data.month, data.year, data.address, data.city, data.id_state,
                data.zipCode, data.country, data.phoneNumber);
        logger.info("Registration - success");
        accountPage.searchItem(DataReader.item);
        searchPage.addToCart();
        logger.info("Item added to the cart - success");
        wait.until(ExpectedConditions.visibilityOf(shoppingCartPage.getCheckOut()));
        shoppingCartPage.proceedToCheckout();
        logger.info("Order placed - success");
        shoppingCartPage.goToOrders();
        boolean isExist = accountPage.findOrderTable();
        Assert.assertTrue(isExist, "Cart is empty");
        logger.info("Test - success");
    }

}
