package Base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class TestUti {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    public static String url, browser;
    private int implicitWait;

    // Използвай този метод, когато ти трябва WebDriver в тестовете
    public WebDriver getDriver() {
        return driver.get();
    }

    @BeforeMethod
    public void beforeEachTest() {
        readConfig("src/test/resources/config.properties");
        setupDriver();
        getDriver().get(url);
    }

    @AfterMethod
    public void tearDown() {
        getDriver().quit();
        driver.remove();
    }

    private void readConfig(String filePath) {
        try (FileInputStream fileInputStream = new FileInputStream(filePath)) {
            Properties properties = new Properties();
            properties.load(fileInputStream);

            browser = properties.getProperty("browser");
            url = properties.getProperty("testUrl");
            implicitWait = Integer.parseInt(properties.getProperty("implicitWait"));

        } catch (IOException e) {
            throw new RuntimeException("Cannot read config file", e);
        }
    }

    private void setupDriver() {
        WebDriver localDriver;

        switch (browser.toLowerCase()) {
            case "firefox":
                localDriver = setupFirefoxDriver();
                break;
            case "edge":
                localDriver = setupEdgeDriver();
                break;
            default:
                localDriver = setupChromeDriver();
        }

        driver.set(localDriver);
    }

    private WebDriver setupFirefoxDriver() {
        WebDriverManager.firefoxdriver().driverVersion("0.33.0").setup();
        return new FirefoxDriver();
    }

    private WebDriver setupEdgeDriver() {
        WebDriverManager.edgedriver().setup();
        return new EdgeDriver();
    }

    private WebDriver setupChromeDriver() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-features=PasswordLeakDetection");

        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        prefs.put("profile.password_manager_leak_detection", false);
        options.setExperimentalOption("prefs", prefs);

        return new ChromeDriver(options);
    }
}
