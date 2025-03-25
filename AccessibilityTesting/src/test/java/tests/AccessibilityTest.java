package tests;

import com.deque.axe.AXE;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.URL;

import static org.testng.Assert.assertTrue;

public class AccessibilityTest {
    private WebDriver driver;
    private static final URL AXE_SCRIPT = AccessibilityTest.class.getClassLoader().getResource("axe.min.js");
    private static final int ALLOWED_VIOLATIONS = 5; // Allowed accessibility issues before failing

    @BeforeClass
    public void setUp() {
        System.out.println("🔍 Initializing ChromeDriver for Accessibility Test...");

        try {
            WebDriverManager.chromedriver().setup(); // Ensures correct ChromeDriver version
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--start-maximized");

            driver = new ChromeDriver(options);
            System.out.println("✅ ChromeDriver successfully launched!");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("❌ ERROR: ChromeDriver failed to initialize.");
            assertTrue(false, "WebDriver initialization failed.");
        }
    }

    @Test
    public void testAmazonAccessibility() {
        if (driver == null) {
            System.err.println("❌ ERROR: WebDriver is NULL! Test cannot proceed.");
            assertTrue(false, "WebDriver failed to initialize.");
        }

        driver.get("https://www.amazon.in");

        System.out.println("🔍 Checking axe.min.js path: " + AXE_SCRIPT);
        if (AXE_SCRIPT == null) {
            System.err.println("❌ ERROR: axe.min.js not found in src/test/resources!");
            assertTrue(false, "axe.min.js is missing.");
        }

        JSONObject responseJson = new AXE.Builder(driver, AXE_SCRIPT).analyze();
        JSONArray violations = responseJson.getJSONArray("violations");

        System.out.println("🔍 Total Accessibility Violations Found: " + violations.length());

        // Ignore specific issues (e.g., 'color-contrast')
        JSONArray filteredViolations = new JSONArray();
        for (int i = 0; i < violations.length(); i++) {
            JSONObject violation = violations.getJSONObject(i);
            if (!violation.getString("id").equals("color-contrast")) { // Ignore color contrast issues
                filteredViolations.put(violation);
            }
        }

        if (filteredViolations.length() == 0) {
            System.out.println("✅ No critical accessibility issues found.");
        } else if (filteredViolations.length() <= ALLOWED_VIOLATIONS) {
            System.out.println("⚠️ Accessibility issues found, but within the allowed limit.");
        } else {
            AXE.writeResults("AmazonAccessibilityTest", responseJson);
            System.out.println("❌ Critical accessibility issues found: " + filteredViolations);
            assertTrue(false, "Too many accessibility violations detected!");
        }
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            System.out.println("✅ Browser closed successfully.");
        }
    }
}
