package firstLoginTest;

import Base.TestUti;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class Unsuccessfull extends TestUti {

    @Test(dataProvider = "wrongUsers")
    public void unsuccessfull(String userName, String password) {
        WebDriver driver = getDriver(); // Взимаме драйвера правилно

        driver.findElement(By.id("user-name")).click();
        driver.findElement(By.id("user-name")).clear();
        driver.findElement(By.id("user-name")).sendKeys(userName); // използваме стойността

        driver.findElement(By.id("password")).click();
        driver.findElement(By.id("password")).clear();
        driver.findElement(By.id("password")).sendKeys(password); // използваме стойността

        driver.findElement(By.id("login-button")).click();

        WebElement errorMsg = driver.findElement(By.cssSelector("[data-test=error]"));

        Assert.assertTrue(errorMsg.isDisplayed());
        Assert.assertEquals(errorMsg.getText(),
                "Epic sadface: Username and password do not match any user in this service");
    }

    @DataProvider(name = "wrongUsers")
    public Object[][] readWrongUsers() {
        try (CSVReader csvReader = new CSVReader(new FileReader("src/test/resources/wrongUsers.csv"))) {
            List<String[]> csvData = csvReader.readAll();
            Object[][] csvResult = new Object[csvData.size()][2];

            for (int i = 0; i < csvData.size(); i++) {
                csvResult[i] = csvData.get(i);
            }

            return csvResult;

        } catch (IOException | CsvException e) {
            throw new RuntimeException("Проблем при четене на CSV файла", e);
        }
    }
}
