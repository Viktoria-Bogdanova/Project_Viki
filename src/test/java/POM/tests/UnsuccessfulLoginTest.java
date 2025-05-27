package POM.tests;

import POM.pages.LoginPage;
import Base.TestUti;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class UnsuccessfulLoginTest extends TestUti {

    @Test(dataProvider = "wrongUsers")
    public void unsuccessfullLoginTest(String userName, String password) {
        LoginPage loginPage = new LoginPage(getDriver()); // Използваме getDriver()
        loginPage.login(userName, password);

        Assert.assertTrue(loginPage.isErrorDisplayed(), "Error message not displayed!");
        Assert.assertEquals(
                loginPage.getErrorMessage(),
                "Epic sadface: Username and password do not match any user in this service"
        );
    }

    @DataProvider(name = "wrongUsers")
    public Object[][] readWrongUsers() {
        try (CSVReader csvReader = new CSVReader(new FileReader("src/test/resources/wrongUsers.csv"))) {
            List<String[]> csvData = csvReader.readAll();
            Object[][] data = new Object[csvData.size()][2];

            for (int i = 0; i < csvData.size(); i++) {
                data[i] = csvData.get(i);
            }
            return data;

        } catch (IOException | CsvException e) {
            throw new RuntimeException("Error reading CSV file", e);
        }
    }
}
