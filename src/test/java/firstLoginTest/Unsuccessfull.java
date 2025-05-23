package firstLoginTest;

import Base.TestUti;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Unsuccessfull extends TestUti {
    @Test
    public void unsuccessfull(){

        driver.findElement(By.id("user-name")).click();
        driver.findElement(By.id("user-name")).clear();
        driver.findElement(By.id("user-name")).sendKeys("standard_user777");

        driver.findElement(By.id("password")).click();
        driver.findElement(By.id("password")).clear();
        driver.findElement(By.id("password")).sendKeys("secret_sauce");

        driver.findElement(By.id("login-button")).click();


        //check test-validation
        WebElement errorMsg = driver.findElement(By.cssSelector("[data-test=error]"));
        Assert.assertTrue(errorMsg.isDisplayed());
        Assert.assertEquals(errorMsg.getText(),
                "Epic sadface: Username and password do not match any user in this service");



    }
}
