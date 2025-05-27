package POM.tests;

import Base.TestUti;
import POM.pages.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckOuts extends TestUti {

    @Test
    public void completeCheckoutSuccessfully() {
        System.out.println("Test for successful order begins");

        //Login
        LoginPage loginPage = new LoginPage(getDriver());  // поправено
        ProductPage productPage = loginPage.login("standard_user", "secret_sauce");

        //Add Items
        productPage.addItemToCart("bike-light");
        productPage.addItemToCart("bolt-t-shirt");
        productPage.addItemToCart("fleece-jacket");

        // Go to cart
        CartPage cartPage = productPage.goToCart();

        // Click Checkout
        CheckoutStepOnePage checkoutStepOnePage = cartPage.clickCheckout();

        // Data for costumer
        CheckoutStepTwoPage checkoutStepTwoPage = checkoutStepOnePage.fillFormAndContinue("Иван", "Иванов", "1000");

        // Finish Order
        CompletePage completePage = checkoutStepTwoPage.finishCheckout();


        Assert.assertTrue(completePage.isAt(), "The order was not successfully completed!");
        Assert.assertEquals(completePage.getCompleteMessage(), "Thank you for your order!", "Message does not match.");
    }
}
