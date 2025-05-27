package Testsproducts;

import Base.TestUti;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class AddItems extends TestUti {
    private final static String BASE_PRODUCT_ID = "add-to-cart-sauce-labs-";

    @Test(dataProvider = "shoppingItems")
    public void addProductsToCart(String item) {
        // Използваме getDriver() вместо директен достъп до driver
        WebElement usernameInput = getDriver().findElement(By.id("user-name"));
        usernameInput.click();
        usernameInput.clear();
        usernameInput.sendKeys("standard_user");

        WebElement passwordInput = getDriver().findElement(By.id("password"));
        passwordInput.click();
        passwordInput.clear();
        passwordInput.sendKeys("secret_sauce");

        WebElement loginBtn = getDriver().findElement(By.id("login-button"));
        loginBtn.click();

        WebElement itemToBeAdded = getDriver().findElement(By.id(BASE_PRODUCT_ID + item));
        itemToBeAdded.click();

        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(5));
        WebElement shoppingCartBadge = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".shopping_cart_badge"))
        );

        Assert.assertEquals(
                shoppingCartBadge.getText(),
                "1",
                "We've added only one item"
        );
    }

    @DataProvider(name = "shoppingItems")
    public Object[] getshoppingCartItems() {
        return new Object[]{
                "onesie",
                "bike-light",
                "backpack"
        };
    }
}
