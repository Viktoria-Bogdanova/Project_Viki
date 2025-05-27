package POM.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProductPage extends BasePage {
    //to find elements
    @FindBy(className = "title")
    private WebElement pageTitle;

    @FindBy(id = "react-burger-menu-btn")
    private WebElement burgerMenu;

    @FindBy(id = "logout_sidebar_link")
    private WebElement logoutBtn;

    @FindBy(className = "shopping_cart_link")
    private WebElement cartIcon;
//constructor
    public ProductPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
// check is the page load
    @Override
    public boolean isAt() {
        return wait.until(ExpectedConditions.visibilityOf(pageTitle)).isDisplayed();
    }
//click burger menu and logout
    public LoginPage logout() {
        burgerMenu.click();
        wait.until(ExpectedConditions.elementToBeClickable(logoutBtn)).click();
        return new LoginPage(driver);
    }
//to add items to cart
    public void addItemToCart(String itemName) {
        String buttonId = "add-to-cart-sauce-labs-" + itemName;
        By addButtonLocator = By.id(buttonId);
        WebElement addButton = wait.until(ExpectedConditions.elementToBeClickable(addButtonLocator));
        addButton.click();
    }
//to count items in cart
    public int getCartItemCount() {
        try {
            WebElement badge = wait.until(ExpectedConditions.presenceOfElementLocated(By.className("shopping_cart_badge")));
            return Integer.parseInt(badge.getText());
        } catch (org.openqa.selenium.TimeoutException e) {
            return 0; // if cart is empty
        }
    }
//to go to cart
    public CartPage goToCart() {
        wait.until(ExpectedConditions.elementToBeClickable(cartIcon)).click();
        return new CartPage(driver);
    }
}
