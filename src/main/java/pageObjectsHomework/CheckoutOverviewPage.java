package pageObjectsHomework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckoutOverviewPage {
    private final WebDriver driver;
    public CheckoutOverviewPage(WebDriver driver) {this.driver = driver;}
    private final By finishButton = By.id("finish");
    private final By backHomeButton = By.id("back-to-products");

    private final By inventoryItemName = By.className("inventory_item_name");
    public void finishCheckout() {
        driver.findElement(finishButton).click();
    }
    public WebElement getinventoryItemName() {return driver.findElement(inventoryItemName);}
    public String getinventoryItemNameText() {return getinventoryItemName().getText();}
    public void clickBackHome() {
        driver.findElement(backHomeButton).click();
    }

}
