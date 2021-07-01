import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Properties;

public class Register {
    HomePage homePage;
    AuthentificationPage authentificationPage;
    FormPage formPage;

    @Test
    public void register() throws FileNotFoundException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, 15);
        homePage = new HomePage(driver);
        authentificationPage = new AuthentificationPage(driver);
        formPage = new FormPage(driver);

        FileInputStream prop;
        Properties property = new Properties();
        try {
            prop = new FileInputStream("config.properties");
            property.load(prop);

        } catch (IOException e) {
            System.err.println("ОШИБКА: Файл свойств отсуствует!");
        }
        String name = property.getProperty("name");
        String lastName = property.getProperty("lastName");
        String passwrd = property.getProperty("passwrd");
        String address = property.getProperty("address");
        String city = property.getProperty("city");
        String zipCode = property.getProperty("zipCode");
        String phoneNumber = property.getProperty("phoneNumber");
        String url = property.getProperty("url");

        driver.get(url);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("login")));
        homePage.clickToSignIn();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='email_create']")));

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Long number = timestamp.getTime();
        String email = number + "@mail.ru";

        authentificationPage.enterEmail(email);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='uniform-id_gender1']")));
        formPage.chooseTitle();
        formPage.enterFirstName(name);
        formPage.enterLastName(lastName);
        formPage.enterPassword(passwrd);
        formPage.selectDate();
        formPage.selectMonth();
        formPage.selectYear();
        formPage.enterAddress(address);
        formPage.enterCity(city);
        formPage.selectState();
        formPage.enterZip(zipCode);
        formPage.selectCountry();
        formPage.enterPhoneNumber(phoneNumber);
        formPage.submitForm();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[contains(text(), 'My account')]")));
        String n = driver.getTitle();
        Assert.assertEquals(n, "My account - My Store", "Registration failed");

        driver.close();
    }
}
