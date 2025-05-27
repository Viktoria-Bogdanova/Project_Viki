package POM.tests;

import Base.TestUti;
import POM.pages.LoginPage;
import POM.pages.ProductPage;
import POM.pages.CartPage;
import POM.pages.CheckoutPage;
import POM.pages.CheckoutOverviewPage;
import POM.pages.CheckoutCompletePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckOuts extends TestUti {

    @Test
    public void completeCheckoutWithItems() {
        System.out.println("=== Започва тест за Checkout с 3 продукта ===");

        // Стъпка 1: Логин
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        // Стъпка 2: Добавяне на 3 продукта
        ProductPage productPage = new ProductPage(driver);
        String[] items = {"bike-light", "bolt-t-shirt", "fleece-jacket"};

        for (String item : items) {
            productPage.addItemToCart(item);
        }

        // Стъпка 3: Навигиране до количката
        productPage.goToCart();

        // Стъпка 4: Натискане на Checkout бутона
        CartPage cartPage = new CartPage(driver);
        cartPage.clickCheckout();

        // Стъпка 5: Въвеждане на данни
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.enterCheckoutInfo("Име", "Фамилия", "1000");

        // Стъпка 6: Финализиране на поръчката
        CheckoutOverviewPage overviewPage = new CheckoutOverviewPage(driver);
        overviewPage.clickFinish();

        // Стъпка 7: Потвърждение за успешна поръчка
        CheckoutCompletePage completePage = new CheckoutCompletePage(driver);
        Assert.assertTrue(completePage.isOrderComplete(), "Поръчката не беше завършена успешно!");

        System.out.println("=== Поръчката е завършена успешно ===");
    }
}
