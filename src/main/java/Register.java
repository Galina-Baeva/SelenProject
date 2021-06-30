import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.sql.Timestamp;

public class Register {
    HomePage homePage;
    AuthentificationPage authentificationPage;
    FormPage formPage;

    @Test
    public void register() {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, 15);
        homePage = new HomePage(driver);
        authentificationPage = new AuthentificationPage(driver);
        formPage = new FormPage(driver);
        driver.get("http://automationpractice.com");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("login")));

        homePage.clickToSignIn();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='email_create']")));

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Long number = timestamp.getTime();
        String email = number + "@mail.ru";

        authentificationPage.enterEmail(email);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='uniform-id_gender1']")));
        ;

        formPage.chooseTitle();
        formPage.enterFirstName();
        formPage.enterLastName();
        formPage.enterPassword();
        formPage.selectDate();
        formPage.selectMonth();
        formPage.selectYear();
        formPage.enterAddress();
        formPage.enterCity();
        formPage.selectState();
        formPage.enterZip();
        formPage.selectCountry();
        formPage.enterPhoneNumber();
        formPage.submitForm();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[contains(text(), 'My account')]")));

        String n = driver.getTitle();
        Assert.assertEquals(n, "My account - My Store", "Registration failed");

        driver.close();
    }
}
