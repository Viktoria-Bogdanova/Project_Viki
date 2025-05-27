package POM.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CompletePage extends BasePage {

    @FindBy(className = "complete-header")
    private WebElement completeMessage;
//constructor
    public CompletePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
//method to take text from message
    public String getCompleteMessage() {
        return completeMessage.getText();

    }
    //implement isAt
    @Override
    public boolean isAt() {
        return completeMessage.isDisplayed();
    }
}
