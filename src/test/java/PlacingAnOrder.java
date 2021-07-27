import io.github.bonigarcia.wdm.WebDriverManager;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.*;
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
    RegisterTest registerTest = new RegisterTest();
    DataReader data;
    WebDriver driver;
    WebDriverWait wait;
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
        data = new DataReader(path);

        driver.get(url + urlAccountPage);
        wait.until(ExpectedConditions.visibilityOf(authentificationPage.getEmailAcc()));
        authentificationPage.logIn(email, DataReader.passwrd);
        logger.info("LogIn - success");
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
