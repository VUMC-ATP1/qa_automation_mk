package pageObjectsHomework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
    private final WebDriver driver;

    private final String SAUCE_INVENTORY_PAGE = "https://www.saucedemo.com/inventory.html";

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }
    private final By firstNameField = By.id("user-name");
    private final By lastNameField = By.id("password");
    private final By login = By.id("login-button");

    public WebElement getFirstNameField() {
        return driver.findElement(firstNameField);
    }
    public void setFirstNameField (String firstName) {
        getFirstNameField().sendKeys(firstName);
    }

    public WebElement getLastNameField() {
        return driver.findElement(lastNameField);
    }
    public void setLastNameField(String lastName) {
        getLastNameField().sendKeys(lastName);
    }
    public WebElement getLogin() { return driver.findElement(login);}
    public void login (){
        getLogin().click();
    }

}
