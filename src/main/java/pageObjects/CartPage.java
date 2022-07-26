package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.WebDriverManager;

public class CartPage {
    private final WebDriver driver = WebDriverManager.getInstance();
    private final By cartItem = By.xpath("//div[@class='inventory_item_name']");
    private final By checkOutButton = By.xpath("//button[@data-test='checkout' and @id='checkout']");

    public WebElement getCheckOutButton() {
        return driver.findElement(By.xpath("//button[@data-test='checkout' and @id='checkout']"));
    }

    public WebElement getCartItem() {
        return driver.findElement(cartItem);
    }

    public void startCheckout() {
        getCheckOutButton().click();
    }
}
