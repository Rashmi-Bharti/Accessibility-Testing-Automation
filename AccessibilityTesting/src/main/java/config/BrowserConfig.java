package config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BrowserConfig {
    private static final Logger LOGGER = Logger.getLogger(BrowserConfig.class.getName());
    private static WebDriver driver;

    public static WebDriver startBrowser(String browserName) {
        try {
            if (browserName == null || browserName.isEmpty()) {
                browserName = "chrome";  // Default to Chrome if not specified
            }

            switch (browserName.toLowerCase()) {
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.addArguments("--start-maximized"); // Maximize window
                    chromeOptions.addArguments("--disable-notifications"); // Disable pop-ups
                    // Uncomment for headless mode: chromeOptions.addArguments("--headless");
                    driver = new ChromeDriver(chromeOptions);
                    break;

                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    firefoxOptions.addArguments("--width=1920");
                    firefoxOptions.addArguments("--height=1080");
                    driver = new FirefoxDriver(firefoxOptions);
                    break;

                case "edge":
                    WebDriverManager.edgedriver().setup();
                    EdgeOptions edgeOptions = new EdgeOptions();
                    edgeOptions.addArguments("--start-maximized");
                    driver = new EdgeDriver(edgeOptions);
                    break;

                default:
                    LOGGER.warning("Invalid browser name provided: " + browserName + ". Launching Chrome as default.");
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                    break;
            }

            // Set Implicit Wait for element loading
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            LOGGER.info(browserName.toUpperCase() + " Browser Launched Successfully!");

        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "❌ Error launching browser: ", e);
        }
        return driver;
    }

    public static void closeBrowser() {
        if (driver != null) {
            driver.quit();
            LOGGER.info("✅ Browser closed successfully.");
            driver = null; // Ensure the driver instance is cleared
        }
    }
}
