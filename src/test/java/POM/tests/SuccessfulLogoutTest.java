package POM.tests;

import Base.TestUti;
import POM.pages.LoginPage;
import POM.pages.ProductPage;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class SuccessfulLogoutTest extends TestUti {
@Test
    public void successfulLogoutTest(){
    LoginPage loginPage = new LoginPage(driver);
    ProductPage productPage = loginPage.login("standard_user", "secret_sauce");
    SoftAssert softAssert = new SoftAssert();

    softAssert.assertTrue(productPage.isAt());

    productPage.logout();
    softAssert.assertTrue(loginPage.isAt());
    softAssert.assertAll();


}
}
