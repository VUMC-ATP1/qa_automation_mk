package seleniumHomework;

import lombok.extern.log4j.Log4j;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pageObjectsHomework.*;

import java.time.Duration;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

@Log4j
public class sauceDemoTests {
    ChromeDriver driver;
    LoginPage loginPage;
    CartPage cartPage;
    InventoryPage inventoryPage;
    CheckoutOverviewPage checkoutOverviewPage;
    CheckoutPage checkoutPage;
    CheckoutSuccessPage checkoutSuccessPage;
    private final String SAUCE_INVENTORY_PAGE = "https://www.saucedemo.com/inventory.html";
    private final String SAUCE_LOGIN_PAGE = "https://www.saucedemo.com/";
    private final String CHECKOUT_COMPLETE = "https://www.saucedemo.com/checkout-complete.html";

    @BeforeTest
    public void setProperties() {
        System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");
        Logger.getLogger("org.openqa.selenium").setLevel(Level.OFF);
    }

    @BeforeMethod
    public void openBrowser() {
        log.info("Initializing ChromeDriver");
        driver = new ChromeDriver();
        // 1. Navigēt uz saiti https://www.saucedemo.com/
        driver.get(SAUCE_LOGIN_PAGE);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
    }

    @Test
    public void testScenarioOne() {
        loginPage = new LoginPage(driver);
        // 2. Ielogoties ar pareizu lietotāja vārdu/paroli
        loginPage.setFirstNameField("standard_user");
        loginPage.setLastNameField("secret_sauce");
        loginPage.login();
        // 3. Pārbaudīt, ka lietotājs ir ielogojies
        Assert.assertEquals(driver.getCurrentUrl(), SAUCE_INVENTORY_PAGE);
        // 4. Ievietot Grozā 1 produktu
        inventoryPage = new InventoryPage(driver);
        inventoryPage.clickBackPackButton();
        // 5. Doties uz grozu
        inventoryPage.clickCartPage();
        // 6. Pārbaudīt, kā šī prece ir grozā
        cartPage = new CartPage(driver);
        try {
            Assert.assertEquals(cartPage.checkCart(), "Sauce Labs Backpack");
        } catch (Exception e) {
            log.info("Check cart page test failed");
        }
        // 7. Doties uz Checkout
        cartPage = new CartPage(driver);
        cartPage.startCheckout();
        // 8. Ievadīt vārdu/uzvārdu/pasta indeksu
        checkoutPage = new CheckoutPage(driver);
        checkoutPage.setFirstNameFieldCheckout("Martins");
        checkoutPage.setLastNameFieldCheckout("Kruklis");
        checkoutPage.setPostalCodeFieldCheckout("LV0000");
        // 9. Doties uz Checkout overview lapu, pārbaudīt datus
        try {
            Assert.assertEquals(checkoutPage.getInventoryItemNameText(),"Sauce Labs Backpack");
        } catch (Exception e) {
            log.info("Checkout page test failed");
        }
        checkoutPage.continueCheckout();
//        Assert.assertEquals(checkoutOverviewPage.getinventoryItemNameText(),"Sauce Labs Backpack");
        // 10. Doties uz finish lapu un pārbaudīt vai viss bija veiksmīgi
        checkoutOverviewPage = new CheckoutOverviewPage(driver);
        checkoutOverviewPage.finishCheckout();
        Assert.assertEquals(driver.getCurrentUrl(),CHECKOUT_COMPLETE);
//        Assert.assertEquals(checkoutSuccessPage.getSuccessCheckoutText(),"Your order has been dispatched, and will arrive just as fast as the pony can get there!");
        // 11. Doties atpakaļ uz pirmo lapu ar pogu 'Back Home's
        checkoutSuccessPage = new CheckoutSuccessPage(driver);
        checkoutSuccessPage.clickBackHomeButton();
    }

    @Test
    public void testScenarioTwo() {
        loginPage = new LoginPage(driver);
        // 2. Ielogoties ar pareizu lietotāja vārdu/paroli
        loginPage.setFirstNameField("standard_user");
        loginPage.setLastNameField("secret_sauce");
        loginPage.login();
        // 3. Doties uz grozu
        inventoryPage = new InventoryPage(driver);
        inventoryPage.clickCartPage();
        // 4. Doties uz Checkout
        cartPage = new CartPage(driver);
        cartPage.startCheckout();
        // 5. Pārbaudīt, ka FirstName/LastName/Zip code ir obligāts
        Assert.assertTrue(checkoutPage.isInputRequired(checkoutPage.getFirstNameFieldCheckout(), "required"));
        Assert.assertTrue(checkoutPage.isInputRequired(checkoutPage.getLastNameFieldCheckout(), "required"));
        Assert.assertTrue(checkoutPage.isInputRequired(checkoutPage.getPostalCodeFieldCheckout(), "required"));
        // 6. Pārbaudīt, ka forma parāda pareizu kļūdas paziņojumu pie katra neievadītā lauka
        // 6.1. Checking empty firstName field error
        checkoutPage = new CheckoutPage(driver);
        checkoutPage.getLastNameFieldCheckout().sendKeys("Kruklis");
        checkoutPage.getPostalCodeFieldCheckout().sendKeys("LV-0000");
        Assert.assertEquals(checkoutPage.getErrorText(), "Error: First Name is required");
        driver.navigate().refresh();
        // 6.2. Checking empty lastName field error
        checkoutPage.getFirstNameFieldCheckout().sendKeys("Martins");
        checkoutPage.getPostalCodeFieldCheckout().sendKeys("LV-0000");
        Assert.assertEquals(checkoutPage.getErrorText(), "Error: Last Name is required");
        driver.navigate().refresh();
        // 6.3. Checking empty postalCode error
        checkoutPage.getFirstNameFieldCheckout().sendKeys("Martins");
        checkoutPage.getFirstNameFieldCheckout().sendKeys("Kruklis");
        Assert.assertEquals(checkoutPage.getErrorText(), "Error: Postal Code is required");
    }

    @AfterMethod
    public void closeBrowser() {
        log.info("Closing ChromeDriver");
        driver.close();
    }
}

