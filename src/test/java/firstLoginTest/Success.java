package firstLoginTest;

import Base.TestUti;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;

public class Success extends TestUti {

    @Test(dataProvider = "correctUsers")
    public void success(String userName) {
        WebDriver driver = getDriver(); // Вземаме драйвера чрез метода от TestUti

        driver.findElement(By.id("user-name")).click();
        driver.findElement(By.id("user-name")).clear();
        driver.findElement(By.id("user-name")).sendKeys(userName);

        driver.findElement(By.id("password")).click();
        driver.findElement(By.id("password")).clear();
        driver.findElement(By.id("password")).sendKeys("secret_sauce");

        driver.findElement(By.id("login-button")).click();

        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement productsPageTitle = driver.findElement(By.className("title"));

        explicitWait.until(ExpectedConditions.visibilityOf(productsPageTitle));

        Assert.assertTrue(productsPageTitle.isDisplayed(), "Заглавието 'Products' не се вижда – login неуспешен.");
    }

    @DataProvider(name = "correctUsers", parallel = true)
    public Object[] getCorrectUsers() {
        return new Object[] {
                "standard_user",
                "problem_user",
                "performance_glitch_user",
                "error_user",
                "visual_user"
        };
    }
}
