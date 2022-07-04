package pageObjectsHomework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckoutSuccessPage {
    private final WebDriver driver;
    public CheckoutSuccessPage (WebDriver driver) {
        this.driver = driver;
    }
    private final By successCheckout = By.className("complete-text");
    private final By backHomeButton = By.id("back-to-products");

    public WebElement getSuccessCheckout () {
        return driver.findElement(successCheckout);
    }
    public void clickBackHomeButton() {
        driver.findElement(backHomeButton).click();
    }
    public String getSuccessCheckoutText() {
        return getSuccessCheckout().getText();
    }


}
