package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.Constants;
import utils.WebDriverManager;

public class CheckoutPage {
    private final WebDriver driver = WebDriverManager.getInstance();
    private final By firstNameField = By.xpath("//input[@type='text' and @id='first-name']");
    private final By lastNameField =  By.xpath("//input[@type='text' and @id='last-name']");
    private final By postalCodeField = By.xpath("//input[@type='text' and @id='postal-code']");
    private final By finishCheckoutButton = By.id("finish");
    private final By continueCheckoutButton = By.id("continue");
    private final By backToProducts = By.xpath("//button[@data-test='back-to-products' and @id='back-to-products']");
    private final By errorField = By.xpath("//h3[@data-test='error']");

    public WebElement getFirstNameField() {
        return driver.findElement(firstNameField);
    }

    public WebElement getLastNameField() {
        return driver.findElement(lastNameField);
    }

    public WebElement getPostalCodeField() { return driver.findElement(postalCodeField);}

    public WebElement getFinishCheckoutButton() {return driver.findElement(finishCheckoutButton);}

    public WebElement getBackToProducts()  {return driver.findElement(backToProducts);}

    public WebElement getErrorField() {return driver.findElement(errorField);}
    public WebElement getContinueCheckoutButton() {return driver.findElement(continueCheckoutButton);}

    public void addCheckoutInfo(){
        getFirstNameField().sendKeys(Constants.FIRSTNAME_CHECKOUT);
        getLastNameField().sendKeys(Constants.LASTNAME_CHECKOUT);
        getPostalCodeField().sendKeys(Constants.POSTALCODE_CHECKOUT);
    }

    public void addCheckoutInfo(String firstname, String lastname, String postalcode) {
        getFirstNameField().sendKeys(firstname);
        getLastNameField().sendKeys(lastname);
        getPostalCodeField().sendKeys(postalcode);
    }

    public void finalizeCheckout() {
        getFinishCheckoutButton().click();
        getBackToProducts().click();
    }

    public boolean isInputRequired(WebElement element, String attribute) {
        String value = element.getAttribute(attribute);
        return value != null;
    }

    public String getErrorText() {
        return getErrorField().getText();
    }
}
