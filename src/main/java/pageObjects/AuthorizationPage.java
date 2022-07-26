package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.WebDriverManager;

public class AuthorizationPage {

    WebDriver driver = WebDriverManager.getInstance();

    private final By usernameInputField = By.id("user-name");
    private final By passwordInputField = By.id("password");
    private final By loginButton = By.id("login-button");
    private final By errorField = By.xpath("//h3[@data-test='error']");

    public void goToLogin(String username, String password) {
        driver.findElement(usernameInputField).sendKeys(username);
        driver.findElement(passwordInputField).sendKeys(password);
        driver.findElement(loginButton).click();
    }

    public WebElement getErrorField() {
        return driver.findElement(errorField);
    }

}
