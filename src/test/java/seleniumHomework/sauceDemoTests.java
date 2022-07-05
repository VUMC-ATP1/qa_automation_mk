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
    private final String USER_NAME = "standard_user";
    private final String PASSWORD = "secret_sauce";
    private final String PRODUCT_NAME = "Sauce Labs Backpack";
    private final String FIRSTNAME_CHECKOUT = "Martins";
    private final String LASTNAME_CHECKOUT = "Kruklis";
    private final String POSTALCODE_CHECKOUT = "LV0000";
    private final String SUCCESS_MESSAGE = "Your order has been dispatched, and will arrive just as fast as the pony can get there!";

    @BeforeTest
    public void setProperties() {
        System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");
        Logger.getLogger("org.openqa.selenium").setLevel(Level.OFF);
    }

    @BeforeMethod
    public void openBrowser() {
        log.info("Initializing ChromeDriver");
        driver = new ChromeDriver();
        loginPage = new LoginPage(driver);
        inventoryPage = new InventoryPage(driver);
        cartPage = new CartPage(driver);
        checkoutPage = new CheckoutPage(driver);
        checkoutOverviewPage = new CheckoutOverviewPage(driver);
        checkoutSuccessPage = new CheckoutSuccessPage(driver);
        // 1. Navigēt uz saiti https://www.saucedemo.com/
        driver.get(SAUCE_LOGIN_PAGE);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
    }

    @Test
    public void testScenarioOne() {
        // 2. Ielogoties ar pareizu lietotāja vārdu/paroli
        loginPage.setFirstNameField(USER_NAME);
        loginPage.setLastNameField(PASSWORD);
        loginPage.login();
        // 3. Pārbaudīt, ka lietotājs ir ielogojies
        Assert.assertEquals(driver.getCurrentUrl(), SAUCE_INVENTORY_PAGE);
        // 4. Ievietot Grozā 1 produktu
        inventoryPage.clickBackPackButton();
        // 5. Doties uz grozu
        inventoryPage.clickCartPage();
        // 6. Pārbaudīt, kā šī prece ir grozā
        try {
            Assert.assertEquals(cartPage.checkCart(), PRODUCT_NAME);
        } catch (Exception e) {
            log.info("Test for checking product in the cart failed");
        }
        // 7. Doties uz Checkout
        cartPage.startCheckout();
        // 8. Ievadīt vārdu/uzvārdu/pasta indeksu
        checkoutPage.setFirstNameFieldCheckout(FIRSTNAME_CHECKOUT);
        checkoutPage.setLastNameFieldCheckout(LASTNAME_CHECKOUT);
        checkoutPage.setPostalCodeFieldCheckout(POSTALCODE_CHECKOUT);
        // 9. Doties uz Checkout overview lapu, pārbaudīt datus
        try {
            Assert.assertEquals(checkoutPage.getInventoryItemNameText(),PRODUCT_NAME);
        } catch (Exception e) {
            log.info("Test for checking product in the checkout page failed");
        }
        checkoutPage.continueCheckout();
        try {
            Assert.assertEquals(checkoutOverviewPage.getInventoryItemNameText(),PRODUCT_NAME);
        } catch (Exception e) {
            log.info("Test for checking product in the checkout overview failed");
        }
        // 10. Doties uz finish lapu un pārbaudīt vai viss bija veiksmīgi
        checkoutOverviewPage.finishCheckout();
        try {
            Assert.assertEquals(driver.getCurrentUrl(),CHECKOUT_COMPLETE);
        } catch (Exception e) {
            log.info("Test for checking correct redirect URL failed");
        }
        try {
            Assert.assertEquals(checkoutSuccessPage.getSuccessCheckoutText(),SUCCESS_MESSAGE);
        } catch (Exception e) {
            log.info("Test for checking correct success message failed");
        }
        // 11. Doties atpakaļ uz pirmo lapu ar pogu 'Back Home'
        checkoutSuccessPage.clickBackHomeButton();
    }

    @Test
    public void testScenarioTwo() {
        // 2. Ielogoties ar pareizu lietotāja vārdu/paroli
        loginPage.setFirstNameField(USER_NAME);
        loginPage.setLastNameField(PASSWORD);
        loginPage.login();
        // 3. Doties uz grozu
        inventoryPage.clickCartPage();
        // 4. Doties uz Checkout
        cartPage.startCheckout();
        // 5. Pārbaudīt, ka FirstName/LastName/Zip code ir obligāts
        Assert.assertTrue(checkoutPage.isInputRequired(checkoutPage.getFirstNameFieldCheckout(), "required"));
        Assert.assertTrue(checkoutPage.isInputRequired(checkoutPage.getLastNameFieldCheckout(), "required"));
        Assert.assertTrue(checkoutPage.isInputRequired(checkoutPage.getPostalCodeFieldCheckout(), "required"));
        // 6. Pārbaudīt, ka forma parāda pareizu kļūdas paziņojumu pie katra neievadītā lauka
        // 6.1. Checking empty firstName field error
        checkoutPage.getLastNameFieldCheckout().sendKeys(LASTNAME_CHECKOUT);
        checkoutPage.getPostalCodeFieldCheckout().sendKeys(POSTALCODE_CHECKOUT);
        Assert.assertEquals(checkoutPage.getErrorText(), "Error: First Name is required");
        driver.navigate().refresh();
        // 6.2. Checking empty lastName field error
        checkoutPage.getFirstNameFieldCheckout().sendKeys(FIRSTNAME_CHECKOUT);
        checkoutPage.getPostalCodeFieldCheckout().sendKeys(POSTALCODE_CHECKOUT);
        Assert.assertEquals(checkoutPage.getErrorText(), "Error: Last Name is required");
        driver.navigate().refresh();
        // 6.3. Checking empty postalCode error
        checkoutPage.getFirstNameFieldCheckout().sendKeys(FIRSTNAME_CHECKOUT);
        checkoutPage.getFirstNameFieldCheckout().sendKeys(LASTNAME_CHECKOUT);
        Assert.assertEquals(checkoutPage.getErrorText(), "Error: Postal Code is required");
    }

    @AfterMethod
    public void closeBrowser() {
        log.info("Closing browser");
        driver.close();
        driver.quit();
    }
}

