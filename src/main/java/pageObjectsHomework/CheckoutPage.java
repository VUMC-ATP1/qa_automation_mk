package pageObjectsHomework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckoutPage {
    private final WebDriver driver;
    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
    }
    private final By firstNameFieldCheckout = By.id("first-name");
    private final By lastNameFieldCheckout = By.id("last-name");
    private final By postalCodeFieldCheckout = By.id("postal-code");
    private final By inventoryItemName = By.xpath("//*[@class=\"inventory_item_name\"]");
    private final By continueButton = By.id("continue");
    private final By errorField = By.xpath("//h3[@data-test='error']");

    public WebElement getFirstNameFieldCheckout() {
        return driver.findElement(firstNameFieldCheckout);
    }
    public void setFirstNameFieldCheckout(String firstNameFieldCheckout) {
        getFirstNameFieldCheckout().sendKeys(firstNameFieldCheckout);
    }
    public WebElement getLastNameFieldCheckout() {
        return driver.findElement(lastNameFieldCheckout);
    }
    public void setLastNameFieldCheckout(String lastNameFieldCheckout) {
        getLastNameFieldCheckout().sendKeys(lastNameFieldCheckout);
    }
    public WebElement getPostalCodeFieldCheckout() {
        return driver.findElement(postalCodeFieldCheckout);
    }
    public void setPostalCodeFieldCheckout(String postalCodeFieldCheckout) {
        getPostalCodeFieldCheckout().sendKeys(postalCodeFieldCheckout);
    }
    public WebElement getInventoryItemName() {return driver.findElement(inventoryItemName);}
    public String getInventoryItemNameText() {return getInventoryItemName().getText();}
    public void continueCheckout() {
        driver.findElement(continueButton).submit();
    }
    public boolean isInputRequired(WebElement element, String attribute) {
        String value = element.getAttribute(attribute);
        return value != null;
    }

    public String getErrorText() {
        return driver.findElement(errorField).getText();
    }

}
