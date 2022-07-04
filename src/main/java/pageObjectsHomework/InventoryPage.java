package pageObjectsHomework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class InventoryPage {
    private final WebDriver driver;
    public InventoryPage(WebDriver driver) {
        this.driver = driver;
    }
    private final By pickBackPack = By.id("add-to-cart-sauce-labs-backpack");
    private final By cartPage = By.className("shopping_cart_link");

    public void pickBackPack() {
        driver.findElement(pickBackPack).click();
    }
    public void clickCartPage() {
        driver.findElement(cartPage).click();
    }
}
