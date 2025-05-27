package POM.tests;

import Base.TestUti;
import POM.pages.LoginPage;
import POM.pages.ProductPage;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class AddItemsTest extends TestUti {

    @Test
    public void addMultipleItemsToCart() {
        System.out.println("Започва тест за добавяне на 3 продукта в количката");

        // SoftAssert
        SoftAssert softAssert = new SoftAssert();

        LoginPage loginPage = new LoginPage(getDriver()); // поправено
        ProductPage productPage = loginPage.login("standard_user", "secret_sauce");

        // Списък с ID частите на продуктите
        String[] items = {"bike-light", "bolt-t-shirt", "fleece-jacket"};

        for (String item : items) {
            System.out.println("Добавям продукт: " + item);
            productPage.addItemToCart(item);
        }

        int count = productPage.getCartItemCount();
        System.out.println("Общо продукти в количката: " + count);

        // Проверка с SoftAssert
        softAssert.assertEquals(count, items.length, "Броят на продуктите в количката не съвпада!");

        softAssert.assertAll();
    }
}
