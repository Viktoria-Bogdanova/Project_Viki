package POM.tests;

import Base.TestUti;
import POM.pages.LoginPage;
import POM.pages.ProductPage;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class SuccessfulLogoutTest extends TestUti {

    @Test
    public void successfulLogoutTest() {
        // Използваме getDriver(), вместо директно driver
        LoginPage loginPage = new LoginPage(getDriver());
        ProductPage productPage = loginPage.login("standard_user", "secret_sauce");

        SoftAssert softAssert = new SoftAssert();

        softAssert.assertTrue(productPage.isAt(), "Не се намираме на страницата с продукти след логин.");
        productPage.logout();
        softAssert.assertTrue(loginPage.isAt(), "Не се върнахме на login страницата след logout.");
        softAssert.assertAll();
    }
}
