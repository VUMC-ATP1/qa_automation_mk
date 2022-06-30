import lombok.extern.log4j.Log4j;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.logging.Level;
import java.util.logging.Logger;

@Log4j
public class TestingBrowser {
    private final String FACEBOOK_URL = "https://www.facebook.com/";
    ChromeDriver driver;

    @BeforeTest
    public void setProperties() {
        System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");
        Logger.getLogger("org.openqa.selenium").setLevel(Level.OFF);
    }

    @BeforeMethod
    public void openBrowser() {
        log.info("Initializing ChromeDriver");
        driver = new ChromeDriver();
        driver.get(FACEBOOK_URL);
    }

    @Test
    public void TitleTest1() {
        Assert.assertEquals(driver.getTitle(), "Facebook – log in or sign up");
    }

    @Test
    public void TitleTest2() {
        Assert.assertEquals(driver.getTitle(), "Welcome to Facebook");
    }

    @AfterMethod
    public void closeBrowser() {
        log.info("Closing Browser");
        driver.close();
    }
}
