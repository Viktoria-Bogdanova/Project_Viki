package POM.tests;

import Base.TestUti;
import POM.pages.LoginPage;
import POM.pages.ProductPage;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class SuccessfulLogoutTest extends TestUti {

    @Test
    public void successfulLogoutTest() {
        // Use getDriver(), instead driver
        LoginPage loginPage = new LoginPage(getDriver());
        ProductPage productPage = loginPage.login("standard_user",
                "secret_sauce");

        SoftAssert softAssert = new SoftAssert();

        softAssert.assertTrue(productPage.isAt(),//check if we are at ProductPage after login
                "We are not on the product page after login.");
        productPage.logout();
        softAssert.assertTrue(loginPage.isAt(),
                "We did not return to the login page after logout.");//check if we are return at ProductPage
        softAssert.assertAll();
    }
}
