package practice;

import lombok.extern.log4j.Log4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import page_object.MainPage;
import java.time.Duration;
import java.util.logging.Level;
import java.util.logging.Logger;

@Log4j
public class BrowserTest {

//    private final String GOOGLE_URL = "https://www.google.lv/";
    private final String LOCAL_FILE = "file://" + this.getClass().getResource("/elements.html").getPath();
    ChromeDriver driver;
    MainPage mainPage;
    WebDriverWait wait;

    @BeforeTest
    public void setProperties() {
        System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");
        Logger.getLogger("org.openqa.selenium").setLevel(Level.OFF);
    }

    @BeforeMethod(alwaysRun = true)
    public void openBrowser() {
        log.info("Initializing ChromeDriver");
        driver = new ChromeDriver();
//        driver.get(GOOGLE_URL);
        mainPage = new MainPage(driver);

    }

    @Test(enabled = false)
    public void chromeDriverTest() {
        Assert.assertEquals(driver.getTitle(), "Google");
//        Assert.assertEquals(driver.getCurrentUrl(), GOOGLE_URL);
    }

    @Test(enabled = false)
    public void elementTest() {
        driver.get(LOCAL_FILE);

        //first name field
        WebElement firstName = driver.findElement(By.id("fNameID"));
        firstName.sendKeys("Martins");
        firstName.clear();
        firstName.sendKeys("Oskars");
        firstName.clear();

        //last name field
        WebElement lastName = driver.findElement(By.id("lNameID"));
        lastName.sendKeys("Kruklis");
        lastName.clear();
        lastName.sendKeys("No surname");

        //about me textfield
        WebElement aboutMe = driver.findElement(By.xpath("//*[@id=\"aboutMeID\"]"));
        aboutMe.sendKeys("BLABLABLA");
        aboutMe.clear();
        aboutMe.sendKeys("HELOLEJELOEOEK");

        //check info button
        WebElement checkInfo = driver.findElement(By.id("checkDataID"));
        checkInfo.click();

        //student checkbox
//        WebElement studentCheckBox = driver.findElement(By.id("universitiesID"));
        WebElement studentCheckBox = driver.findElement(By.xpath("//*[@id=\"studentID\"]"));
        studentCheckBox.isSelected();

        //dropdown (must use Select class)
        Select universityDropDown = new Select(driver.findElement(By.id("universitiesID")));
        universityDropDown.selectByValue("RSU");
        universityDropDown.selectByVisibleText("Rīgas Tehniskā universitāte");
        Assert.assertEquals(universityDropDown.getFirstSelectedOption().getText(), "Rīgas Tehniskā universitāte");

        //radio button
        driver.findElement(By.id("javaID")).click();

        //multiselect
        driver.findElement(By.id("colorsID")).click();
    }

    @Test(enabled = false)
    public void pageObject() {
        mainPage.setFirstNameField("Martins");
    }

    @Test
    public void testElements(){
        driver.get(LOCAL_FILE);
        mainPage.getFirstNameField().sendKeys("Martins");
        mainPage.getLastNameField().sendKeys("Kruklis");
        mainPage.selectStudentCheckBox();
        mainPage.getUniversities().selectByValue("RSU");
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    @AfterMethod(alwaysRun = false)
    public void tearDown() {
        log.info("Closing ChromeDriver");
        driver.close();
    }
}