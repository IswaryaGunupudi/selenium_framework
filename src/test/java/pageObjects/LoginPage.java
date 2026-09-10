package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    private By username =
            By.id("user-name");

    private By password =
            By.id("password");

    private By loginButton =
            By.id("login-button");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void enterUsername(String usernameValue) {
        type(username, usernameValue);
    }

    public void enterPassword(String passwordValue) {
        type(password, passwordValue);
    }

    public void clickLogin() {
        click(loginButton);
    }
}