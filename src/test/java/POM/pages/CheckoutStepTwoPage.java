package POM.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutStepTwoPage extends BasePage {

    @FindBy(id = "finish")
    private WebElement finishButton;

    public CheckoutStepTwoPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public CompletePage finishCheckout() {
        finishButton.click();
        return new CompletePage(driver);
    }

    @Override
    public boolean isAt() {
        return finishButton.isDisplayed();
    }
}
