package tests;

import config.BrowserConfig;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class BrowserTest {
    private WebDriver driver;

    @BeforeMethod
    @Parameters("browser")
    public void setup(@Optional("chrome") String browser) { // Default to Chrome if missing
        System.out.println("🟢 Running test on browser: " + browser);
        driver = BrowserConfig.startBrowser(browser);
    }

    @Test
    public void testPageTitle() {
        driver.get("https://www.google.com");
        String title = driver.getTitle();
        Assert.assertTrue(title.contains("Google"), "Title does not match expected.");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            System.out.println("🟢 Browser closed successfully.");
        }
    }
}
