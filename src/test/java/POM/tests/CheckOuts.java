package POM.tests;

import Base.TestUti;
import POM.pages.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckOuts extends TestUti {

    @Test
    public void completeCheckoutSuccessfully() {
        System.out.println("Започва тест за успешно приключване на поръчка");

        // Логваме се
        LoginPage loginPage = new LoginPage(driver);
        ProductPage productPage = loginPage.login("standard_user", "secret_sauce");

        // Добавяме един продукт
        productPage.addItemToCart("bike-light");
        productPage.addItemToCart("bolt-t-shirt");
        productPage.addItemToCart("fleece-jacket");

        // Отиваме до количката
        CartPage cartPage = productPage.goToCart();

        // Кликваме Checkout
        CheckoutStepOnePage checkoutStepOnePage = cartPage.clickCheckout();

        // Попълваме формата
        CheckoutStepTwoPage checkoutStepTwoPage = checkoutStepOnePage.fillFormAndContinue("Иван", "Иванов", "1000");

        // Завършваме поръчката
        CompletePage completePage = checkoutStepTwoPage.finishCheckout();

        // Проверка
        Assert.assertTrue(completePage.isAt(), "Поръчката не беше успешно завършена!");
        Assert.assertEquals(completePage.getCompleteMessage(), "Thank you for your order!", "Съобщението не съвпада.");
    }
}
