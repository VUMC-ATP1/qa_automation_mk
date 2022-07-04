package pageObjectsHomework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
    private final WebDriver driver;
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
        driver.findElement(firstNameField).sendKeys(firstName);
    }

    public WebElement getLastNameField() {
        return driver.findElement(lastNameField);
    }
    public void setLastNameField(String lastName) {
        driver.findElement(lastNameField).sendKeys(lastName);
    }
    public void login (){
        driver.findElement(login).click();
    }

}
