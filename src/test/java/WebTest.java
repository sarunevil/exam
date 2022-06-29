import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.Instant;

public class WebTest extends BaseTest {
    @Test
    void test_user_registration() {
        driver.findElement(By.partialLinkText("Sukurti naują paskyrą")).click();
        WebElement username_field = driver.findElement(By.xpath("//input[@name='username']"));
        String user_name = "vardas" + RandomStringUtils.random(5, true, true);
        username_field.sendKeys(user_name);
        WebElement password_field = driver.findElement(By.xpath("//input[@name='password']"));
        password_field.sendKeys("password");
        WebElement confirm_field = driver.findElement(By.xpath("//input[@id='passwordConfirm']"));
        confirm_field.sendKeys("password");
        confirm_field.sendKeys(Keys.ENTER);
    }

    @Test
    void test_user_good_password() {
        WebElement username_field = driver.findElement(By.xpath("//input[@name='username']"));
        String user_name = "vardasFYzV7";
        username_field.sendKeys(user_name);
        WebElement password_field = driver.findElement(By.xpath("//input[@name='password']"));
        password_field.sendKeys("password");
        password_field.sendKeys(Keys.ENTER);
        driver.navigate().refresh();
        WebElement header2 = driver.findElement(By.xpath("//h2"));
        Assertions.assertEquals("Galimos operacijos: sudėti, atimti, dauginti, dalinti", header2.getText());
    }

    @Test
    void test_user_wrong_password() {
        WebElement username_field = driver.findElement(By.xpath("//input[@name='username']"));
        String user_name = "vardasFYzV7";
        username_field.sendKeys(user_name);
        WebElement password_field = driver.findElement(By.xpath("//input[@name='password']"));
        password_field.sendKeys("blablabla");
        password_field.sendKeys(Keys.ENTER);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        WebElement search_results = wait
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='form-group has-error']")));

        String result_text = search_results.findElement(By.xpath(".//span[2]")).getText();
        System.out.println(result_text);
        Assertions.assertEquals("Įvestas prisijungimo vardas ir/ arba slaptažodis yra neteisingi", result_text);
    }

    @Test
    void test_wrong_user_registration() {
        driver.findElement(By.partialLinkText("Sukurti naują paskyrą")).click();
        WebElement username_field = driver.findElement(By.xpath("//input[@name='username']"));
        String user_name = "vardas" + RandomStringUtils.random(5, true, true);
        username_field.sendKeys(user_name);
        WebElement password_field = driver.findElement(By.xpath("//input[@name='password']"));
        password_field.sendKeys("password");
        WebElement confirm_field = driver.findElement(By.xpath("//input[@id='passwordConfirm']"));
        confirm_field.sendKeys("passwords");
        confirm_field.sendKeys(Keys.ENTER);

        String result_text = driver.findElement(By.xpath(".//span[@id='passwordConfirm.errors']")).getText();
        System.out.println(result_text);
        Assertions.assertEquals("Įvesti slaptažodžiai nesutampa", result_text);
    }
    @Test
    void log_off() {
        WebElement username_field = driver.findElement(By.xpath("//input[@name='username']"));
        String user_name = "vardasFYzV7";
        username_field.sendKeys(user_name);
        WebElement password_field = driver.findElement(By.xpath("//input[@name='password']"));
        password_field.sendKeys("password");
        password_field.sendKeys(Keys.ENTER);
        driver.navigate().refresh();
        WebElement header2 = driver.findElement(By.xpath("//h2"));
        Assertions.assertEquals("Galimos operacijos: sudėti, atimti, dauginti, dalinti", header2.getText());
        driver.findElement(By.partialLinkText("Logout, vardasFYzV7")).click();

    }
}


