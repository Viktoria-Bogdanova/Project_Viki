package Base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import org.openqa.selenium.chrome.ChromeOptions;

public class TestUti {
    public WebDriver driver;
    public static String url, browser;
    private int implicitWait;


    @BeforeMethod
    public void beforeEachTest(){
        readConfig("src/test/resources/config.properties");
        setupDriver();
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWait));
        // - possible, but have a better option
        driver.get(url);
    }

    //to close page
    @AfterMethod
    public void tearDown(){ driver.quit(); }

    private void readConfig(String filePath){
        try{
            FileInputStream fileInputStream = new FileInputStream(filePath);
            Properties properties = new Properties();
            properties.load(fileInputStream);

            browser = properties.getProperty("browser");
            url = properties.getProperty("testUrl");

            implicitWait = Integer.parseInt(properties.getProperty("implicitWait"));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
       private void setupDriver(){
        switch (browser){
            case "firefox":
            driver = setupFirefoxDriver();
            break;

            case "edge":
                driver = setupedgeDriver();
                break;

            default:
                driver = setupChromeDriver();

        }
       }
       private WebDriver setupFirefoxDriver(){
        WebDriverManager.firefoxdriver().driverVersion("0.33.0").setup();
        return new FirefoxDriver();
       }
       private WebDriver setupedgeDriver(){
        WebDriverManager.edgedriver().setup();
        return new EdgeDriver();
       }
       private WebDriver setupChromeDriver(){
        WebDriverManager.chromedriver().setup();

           ChromeOptions options = new ChromeOptions();
           options.addArguments("--disable-features=PasswordLeakDetection");
// за паролата
           Map<String, Object> prefs = new HashMap<>();
           prefs.put("credentials_enable_service", false);                 // no password-saving
           prefs.put("profile.password_manager_enabled", false);           // no “Save password” bubble
           prefs.put("profile.password_manager_leak_detection", false);    // **kills the “Change your password” breach popup**
           options.setExperimentalOption("prefs", prefs);
        return new ChromeDriver(options);
       }

}
