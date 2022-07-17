package seleniumHomework;

import com.sun.tools.internal.jxc.ap.Const;
import lombok.extern.log4j.Log4j;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pageObjectsHomework.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

@Log4j
public class sauceDemoTests {
    ChromeDriver driver;
    SoftAssert softAssert;
    LoginPage loginPage;
    CartPage cartPage;
    InventoryPage inventoryPage;
    CheckoutOverviewPage checkoutOverviewPage;
    CheckoutPage checkoutPage;
    CheckoutSuccessPage checkoutSuccessPage;

    @BeforeTest
    public void setProperties() {
        System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");
        Logger.getLogger("org.openqa.selenium").setLevel(Level.OFF);
    }

    @BeforeMethod
    public void openBrowser() throws FileNotFoundException {
        log.info("Initializing ChromeDriver");
        driver = new ChromeDriver();
        SoftAssert softAssert = new SoftAssert();
        loginPage = new LoginPage(driver);
        inventoryPage = new InventoryPage(driver);
        cartPage = new CartPage(driver);
        checkoutPage = new CheckoutPage(driver);
        checkoutOverviewPage = new CheckoutOverviewPage(driver);
        checkoutSuccessPage = new CheckoutSuccessPage(driver);
        // 1. Navigēt uz saiti https://www.saucedemo.com/
        driver.get(Constants.SAUCE_LOGIN_PAGE);
    }

    @Test(enabled = true)
    public void testScenarioOne() {
        softAssert = new SoftAssert();
        // 2. Ielogoties ar pareizu lietotāja vārdu/paroli
        loginPage.setFirstNameField(Constants.USER_NAME);
        loginPage.setLastNameField(Constants.PASSWORD);
        loginPage.login();
        // 3. Pārbaudīt, ka lietotājs ir ielogojies
        softAssert.assertEquals(driver.getCurrentUrl(), Constants.SAUCE_INVENTORY_PAGE);
        // 4. Ievietot Grozā 1 produktu
        inventoryPage.clickBackPackButton();
        // 5. Doties uz grozu
        inventoryPage.clickCartPage();
        // 6. Pārbaudīt, kā šī prece ir grozā
        softAssert.assertEquals(cartPage.checkCart(), Constants.PRODUCT_NAME);
        // 7. Doties uz Checkout
        cartPage.startCheckout();
        // 8. Ievadīt vārdu/uzvārdu/pasta indeksu
        checkoutPage.setFirstNameFieldCheckout(Constants.FIRSTNAME_CHECKOUT);
        checkoutPage.setLastNameFieldCheckout(Constants.LASTNAME_CHECKOUT);
        checkoutPage.setPostalCodeFieldCheckout(Constants.POSTALCODE_CHECKOUT);
        // 9. Doties uz Checkout overview lapu, pārbaudīt datus
        checkoutPage.continueCheckout();
        softAssert.assertEquals(checkoutPage.getInventoryItemNameText(), Constants.PRODUCT_NAME);
        // 10. Doties uz finish lapu un pārbaudīt vai viss bija veiksmīgi
        checkoutOverviewPage.finishCheckout();
        softAssert.assertEquals(driver.getCurrentUrl(), Constants.CHECKOUT_COMPLETE);
        softAssert.assertEquals(checkoutSuccessPage.getSuccessCheckoutText(), Constants.SUCCESS_MESSAGE);
        // 11. Doties atpakaļ uz pirmo lapu ar pogu 'Back Home'
        checkoutSuccessPage.clickBackHomeButton();
    }

    @Test(enabled = true)
    public void testScenarioTwo() {
        softAssert = new SoftAssert();
        // 2. Ielogoties ar pareizu lietotāja vārdu/paroli
        loginPage.setFirstNameField(Constants.USER_NAME);
        loginPage.setLastNameField(Constants.PASSWORD);
        loginPage.login();
        // 3. Doties uz grozu
        inventoryPage.clickCartPage();
        // 4. Doties uz Checkout
        cartPage.startCheckout();
        // 5. Pārbaudīt, ka FirstName/LastName/Zip code ir obligāts
        softAssert.assertTrue(checkoutPage.isInputRequired(checkoutPage.getFirstNameFieldCheckout(), "required"));
        /*
        alternative?
        String required = checkoutPage.getFirstNameFieldCheckout().getAttribute("required");
        Assert.assertNotNull(required);
         */
        softAssert.assertTrue(checkoutPage.isInputRequired(checkoutPage.getLastNameFieldCheckout(), "required"));
        softAssert.assertTrue(checkoutPage.isInputRequired(checkoutPage.getPostalCodeFieldCheckout(), "required"));
        // 6. Pārbaudīt, ka forma parāda pareizu kļūdas paziņojumu pie katra neievadītā lauka
        // 6.1. Checking empty firstName field error
        checkoutPage.getLastNameFieldCheckout().sendKeys(Constants.LASTNAME_CHECKOUT);
        checkoutPage.getPostalCodeFieldCheckout().sendKeys(Constants.POSTALCODE_CHECKOUT);
        checkoutPage.continueCheckout();
        softAssert.assertEquals(checkoutPage.getErrorText(), "Error: First Name is required");
        driver.navigate().refresh();
        // 6.2. Checking empty lastName field error
        checkoutPage.getFirstNameFieldCheckout().sendKeys(Constants.FIRSTNAME_CHECKOUT);
        checkoutPage.getPostalCodeFieldCheckout().sendKeys(Constants.POSTALCODE_CHECKOUT);
        checkoutPage.continueCheckout();
        softAssert.assertEquals(checkoutPage.getErrorText(), "Error: Last Name is required");
        driver.navigate().refresh();
        // 6.3. Checking empty postalCode error
        checkoutPage.getFirstNameFieldCheckout().sendKeys(Constants.FIRSTNAME_CHECKOUT);
        checkoutPage.getLastNameFieldCheckout().sendKeys(Constants.LASTNAME_CHECKOUT);
        checkoutPage.continueCheckout();
        softAssert.assertEquals(checkoutPage.getErrorText(), "Error: Postal Code is required");
        softAssert.assertAll();
    }


    @AfterMethod
    public void closeBrowser() {
        log.info("Closing browser");
        driver.close();
        driver.quit();
    }
}

