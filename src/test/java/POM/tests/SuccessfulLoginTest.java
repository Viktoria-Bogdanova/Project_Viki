package POM.tests;

import Base.TestUti;
import POM.pages.LoginPage;
import POM.pages.ProductPage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SuccessfulLoginTest extends TestUti {

    @Test(dataProvider = "correctUsers")
    public void loginWithMultipleUsers(String userName) {
        // Login
        LoginPage loginPage = new LoginPage(getDriver());
        ProductPage productPage = loginPage.login(userName, "secret_sauce");

        // Check if we are at the same page
        Assert.assertTrue(productPage.isAt(), "The Login is unsuccessful: " + userName);
    }

    @DataProvider(name = "correctUsers")
    public Object[] getCorrectUsers() {
        return new Object[]{
                "standard_user",
                "problem_user",
                "performance_glitch_user",
                "error_user",
                "visual_user"
        };
    }
}
