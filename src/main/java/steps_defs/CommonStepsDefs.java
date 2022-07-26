package steps_defs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import pageObjects.AuthorizationPage;
import pageObjects.CartPage;
import pageObjects.CheckoutPage;
import pageObjects.InventoryPage;
import utils.Constants;
import utils.WebDriverManager;

public class CommonStepsDefs {
    WebDriver driver = WebDriverManager.getInstance();
    AuthorizationPage authorizationPage = new AuthorizationPage();
    InventoryPage inventoryPage = new InventoryPage();
    CartPage cartPage = new CartPage();
    CheckoutPage checkoutPage = new CheckoutPage();
    SoftAssert softAssert = new SoftAssert();

    @Given("I have navigated to login page")
    public void goToLogin() {
        driver.get(Constants.SAUCE_LOGIN_PAGE);
    }

    @When("I login with {string} and {string}")
    public void authorize(String username, String password) {
        authorizationPage.goToLogin(username, password);
    }

    @Then("I am successfully logged in")
    public void checkSuccessLogin() {
        Assert.assertEquals(driver.getCurrentUrl(), Constants.SAUCE_INVENTORY_PAGE);
    }

    @Given("I am in Inventory page")
    public void isInventoryPage() {
        goToLogin();
        authorize(Constants.USER_NAME, Constants.PASSWORD);
        Assert.assertEquals(driver.getCurrentUrl(), Constants.SAUCE_INVENTORY_PAGE);
    }

    @When("I add a Product to Cart")
    public void addProductToCart() {
        inventoryPage.addToCart();
    }

    @Then("a Product is added to Cart")
    public void isProductAdded() {
        Assert.assertEquals(inventoryPage.getCartIcon().getText(), "1");
    }

    @Given("I am in Cart page")
    public void isCartPage() {
        goToLogin();
        authorize(Constants.USER_NAME, Constants.PASSWORD);
        inventoryPage.addToCart();
        driver.navigate().to(Constants.SAUCE_CART_PAGE);
        Assert.assertEquals(driver.getCurrentUrl(), Constants.SAUCE_CART_PAGE);
    }

    @When("I check my Cart for the product I added")
    public void checkCart() {
        Assert.assertEquals(cartPage.getCartItem().getText(), Constants.PRODUCT_NAME);
    }

    @Then("I am able to start the checkout")
    public void startCheckout() {
        cartPage.startCheckout();
    }

    @Given("I am in Checkout first step page")
    public void isCheckoutFirstStep() {
        goToLogin();
        authorize(Constants.USER_NAME, Constants.PASSWORD);
        inventoryPage.addToCart();
        driver.navigate().to(Constants.SAUCE_CART_PAGE);
        cartPage.startCheckout();
        Assert.assertEquals(driver.getCurrentUrl(), Constants.CHECKOUT_FIRST_STEP);
    }

    @When("I input complete details for all three fields")
    public void addCompleteDetailsToCheckout() {
        checkoutPage.addCheckoutInfo();
        checkoutPage.getContinueCheckoutButton().click();
    }
    @Then("I am able to proceed to Checkout overview and finish checkout")
    public void checkCheckoutSuccess() {
        checkoutPage.finalizeCheckout();
        Assertions.assertThat(inventoryPage.getCartIcon().getText()).isEqualTo("");
    }

    @When("I check for required attribute for the form input fields")
    public void checkInputRequired() {
        softAssert.assertTrue(checkoutPage.isInputRequired(checkoutPage.getFirstNameField(), "required"));
        softAssert.assertTrue(checkoutPage.isInputRequired(checkoutPage.getLastNameField(), "required"));
        softAssert.assertTrue(checkoutPage.isInputRequired(checkoutPage.getPostalCodeField(), "required"));
    }

    @Then("I see which are required fields")
    public void logRequiredFields() {
        System.out.println("First name is required: " + checkoutPage.isInputRequired(checkoutPage.getFirstNameField(), "required"));
        System.out.println("Last name is required: " + checkoutPage.isInputRequired(checkoutPage.getLastNameField(), "required"));
        System.out.println("Postal/ZIP code is required: " + checkoutPage.isInputRequired(checkoutPage.getPostalCodeField(), "required"));
        softAssert.assertAll();
    }

    @When("I input incomplete details for {string}, {string} and {string}")
    public void addIncompleteDetailsToCheckout(String firstname, String lastname, String postalcode) {
        checkoutPage.addCheckoutInfo(firstname, lastname, postalcode);
        checkoutPage.getContinueCheckoutButton().click();
    }

    @Then("I see error message")
    public void checkErrorMessage() {
        System.out.println((checkoutPage.getErrorText()));
    }
}
