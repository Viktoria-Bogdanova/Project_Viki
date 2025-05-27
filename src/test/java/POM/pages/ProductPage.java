package POM.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProductPage extends BasePage {

    @FindBy(className = "title")
    private WebElement pageTitle;

    @FindBy(id = "react-burger-menu-btn")
    private WebElement burgerMenu;

    @FindBy(id = "logout_sidebar_link")
    private WebElement logoutBtn;

    @FindBy(className = "shopping_cart_link")
    private WebElement cartIcon;

    public ProductPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Override
    public boolean isAt() {
        return wait.until(ExpectedConditions.visibilityOf(pageTitle)).isDisplayed();
    }

    public LoginPage logout() {
        burgerMenu.click();
        wait.until(ExpectedConditions.elementToBeClickable(logoutBtn)).click();
        return new LoginPage(driver);
    }

    public void addItemToCart(String itemName) {
        String buttonId = "add-to-cart-sauce-labs-" + itemName;
        By addButtonLocator = By.id(buttonId);
        WebElement addButton = wait.until(ExpectedConditions.elementToBeClickable(addButtonLocator));
        addButton.click();
    }

    public int getCartItemCount() {
        try {
            WebElement badge = wait.until(ExpectedConditions.presenceOfElementLocated(By.className("shopping_cart_badge")));
            return Integer.parseInt(badge.getText());
        } catch (org.openqa.selenium.TimeoutException e) {
            return 0; // ако няма значка – количката е празна
        }
    }

    public CartPage goToCart() {
        wait.until(ExpectedConditions.elementToBeClickable(cartIcon)).click();
        return new CartPage(driver);
    }
}
