package POM.tests;

import Base.TestUti;
import POM.pages.LoginPage;
import POM.pages.ProductPage;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class AddItemsTest extends TestUti {

    @Test
    public void addMultipleItemsToCart() {
        System.out.println("Test starts with adding 3 items in the cart.");

        // SoftAssert
        SoftAssert softAssert = new SoftAssert();

        LoginPage loginPage = new LoginPage(getDriver());
        ProductPage productPage = loginPage.login("standard_user", "secret_sauce");

        // ID of the products
        String[] items = {"bike-light", "bolt-t-shirt", "fleece-jacket"};

        for (String item : items) {
            System.out.println("Add Product: " + item);
            productPage.addItemToCart(item);
        }

        int count = productPage.getCartItemCount();
        System.out.println("All products in the cart: " + count);

        // SoftAssert
        softAssert.assertEquals(count, items.length, "The number of products in the cart does not match!");

        softAssert.assertAll();
    }
}
