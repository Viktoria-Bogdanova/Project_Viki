package POM.tests;

import Base.TestUti;
import POM.pages.LoginPage;
import POM.pages.ProductPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SuccessfulLoginTest extends TestUti {
    @Test
    public void successfulLoginTest(){
        LoginPage loginPage = new LoginPage(driver);
        ProductPage productPage = loginPage.login("standard_user", "secret_sauce");
        Assert.assertTrue(productPage.isAt());
    }

}
