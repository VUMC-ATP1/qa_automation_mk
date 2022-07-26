package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.WebDriverManager;

public class InventoryPage {
    private final WebDriver driver = WebDriverManager.getInstance();
    private final By addToCartButton = By.xpath("//button[@data-test='add-to-cart-sauce-labs-backpack' and @id='add-to-cart-sauce-labs-backpack']");
    private final By cartIcon = By.xpath("//a[@class='shopping_cart_link']");

    public WebElement getAddToCartButton() {
        return driver.findElement(addToCartButton);
    }

    public WebElement getCartIcon() {
        return driver.findElement(cartIcon);
    }

    public void addToCart() {
        getAddToCartButton().click();
    }

}
