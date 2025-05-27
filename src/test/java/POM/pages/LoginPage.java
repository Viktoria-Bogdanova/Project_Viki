package POM.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage {

    @FindBy(id = "user-name")
    private WebElement userNameInput;

    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy(id = "login-button")
    private WebElement loginBtn;

    @FindBy(css = "[data-test='error']")
    private WebElement errorMessage;

    // Constructor
    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Override
    public boolean isAt() {
        return loginBtn.isDisplayed();
    }

    // Method for successful/unsuccessful login
    public ProductPage login(String userName, String password) {
        userNameInput.click();
        userNameInput.clear();
        userNameInput.sendKeys(userName);

        passwordInput.click();
        passwordInput.clear();
        passwordInput.sendKeys(password);

        loginBtn.click();
        return new ProductPage(driver);

    }

    // Check if error message is displayed
    public boolean isErrorDisplayed() {
        return errorMessage.isDisplayed();
    }

    // Get the error message text
    public String getErrorMessage() {
        return errorMessage.getText();
    }
}
