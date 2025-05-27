package POM.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.time.Duration;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.By;

public class ProductPage extends BasePage {
    @FindBy(className = "title")
    private WebElement pageTitle;

    @FindBy(id = "react-burger-menu-btn")
    private WebElement burgerMenu;

    @FindBy(id = "logout_sidebar_link")
    private WebElement logoutBtn;

    public ProductPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Override
    public boolean isAt() {
        return pageTitle.isDisplayed();
    }

    public LoginPage logout() {
        burgerMenu.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(logoutBtn));


        logoutBtn.click();

        return new LoginPage(driver);
    }
    // Добавяме метод за добавяне на продукт по име (itemName)
    public void addItemToCart(String itemName) {
        String buttonId = "add-to-cart-sauce-labs-" + itemName;
        WebElement addButton = driver.findElement(By.id(buttonId));
        addButton.click();
    }
    // Добави този метод тук, в класа ProductPage
    public int getCartItemCount() {
        try {
            WebElement badge = driver.findElement(By.className("shopping_cart_badge"));
            return Integer.parseInt(badge.getText());
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return 0; // ако няма елемент, значи няма добавени продукти
        }
    }
}
