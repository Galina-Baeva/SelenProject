import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.sql.Timestamp;

import java.util.concurrent.TimeUnit;

public class Register {

    @Test
    public void register() {

        System.setProperty("webdriver.chrome.driver", "C:\\Tools\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("http://automationpractice.com");

        WebElement signIn = driver.findElement(By.className("login"));
        signIn.click();

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Long number = timestamp.getTime();
        String email = number + "@mail.ru";

        WebElement createAcc = driver.findElement(By.xpath("//input[@name='email_create']"));
        createAcc.sendKeys(String.valueOf(email), Keys.ENTER);

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        WebElement formTitle = driver.findElement(By.xpath("//div[@id='uniform-id_gender1']"));
        formTitle.click();

        WebElement formFirstName = driver.findElement(By.xpath("//input[@name='customer_firstname']"));
        formFirstName.sendKeys("Ivanov");

        WebElement formLastName = driver.findElement(By.xpath("//input[@name='customer_lastname']"));
        formLastName.sendKeys("Ivanov");

        WebElement formPassword = driver.findElement(By.xpath("//input[@name='passwd']"));
        formPassword.sendKeys("12345");

        WebElement formDate = driver.findElement(By.xpath("//select[@name='days']"));
        formDate.click();
        formDate.sendKeys("12", Keys.ENTER);

        WebElement formMonth = driver.findElement(By.xpath("//select[@name='months']"));
        formMonth.click();
        formMonth.sendKeys("March", Keys.ENTER);

        WebElement formYear = driver.findElement(By.xpath("//select[@name='years']"));
        formYear.click();
        formYear.sendKeys("1999", Keys.ENTER);

        WebElement addressAddress = driver.findElement(By.xpath("//input[@name='address1']"));
        addressAddress.sendKeys("Fifth street 5");

        WebElement addressCity = driver.findElement(By.xpath("//input[@name='city']"));
        addressCity.sendKeys("Gorod");

        WebElement addressState = driver.findElement(By.xpath("//select[@name='id_state']"));
        addressState.click();
        addressState.sendKeys("New", Keys.ARROW_DOWN, Keys.ARROW_DOWN, Keys.ARROW_DOWN, Keys.ENTER);

        WebElement addressZip = driver.findElement(By.xpath("//input[@name='postcode']"));
        addressZip.sendKeys("12345");

        WebElement addressCountry = driver.findElement(By.id("id_country"));
        addressCountry.click();
        addressCountry.sendKeys("U", Keys.ENTER);

        WebElement addressPhone = driver.findElement(By.xpath("//input[@name='phone_mobile']"));
        addressPhone.sendKeys("3059029325");

        WebElement formRegister = driver.findElement(By.xpath("//button[@name='submitAccount']"));
        formRegister.click();

        try {
            driver.findElement(By.xpath("//h1[contains(text(), 'My account')]"));
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
        finally {
            System.out.println("Account not found");
        }

        driver.close();
    }
}
