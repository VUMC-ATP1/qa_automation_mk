package practice;

import lombok.extern.log4j.Log4j;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.logging.Level;
import java.util.logging.Logger;

@Log4j
public class BrowserTest {

    ChromeDriver driver;

    private final String GOOGLE_URL = "https://www.google.lv";

    @BeforeTest
    public void setProperties() {
        System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");
        Logger.getLogger("orc.openqa.selenium").setLevel(Level.OFF);
    }

    @BeforeMethod(alwaysRun = true)
    public void openBrowser() {
        log.info("Initializing ChromeDriver");
        driver = new ChromeDriver();
        driver.get(GOOGLE_URL);
        driver.close();
    }

    @Test
    public void chromeDriverTest() {
        driver = new ChromeDriver();
        Assert.assertEquals(driver.getWindowHandle(),"Hello");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        log.info("Closing ChromeDriver");
        driver.close();
    }
}
