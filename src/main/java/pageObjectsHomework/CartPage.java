package pageObjectsHomework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CartPage {
    private final WebDriver driver;
    public CartPage(WebDriver driver) {
        this.driver = driver;
    }
    private final By cartElement = By.className("inventory_item_name");
    private final By checkOutButton = By.id("checkout");

    public WebElement getCartElement() {
        return driver.findElement(cartElement);
    }
    public String checkCart() {
        return getCartElement().getText();
    }
    public void startCheckout() {
        driver.findElement(checkOutButton).click();
    }
}
