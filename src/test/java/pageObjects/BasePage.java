package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utilities.WaitUtils;

public class BasePage {

    protected WebDriver driver;
    protected WaitUtils waitUtils;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.waitUtils = new WaitUtils(driver);
    }

    protected void click(By locator) {

        WebElement element = driver.findElement(locator);

        waitUtils.waitForClickable(element);

        element.click();
    }

    protected void type(By locator, String text) {

        WebElement element = driver.findElement(locator);

        waitUtils.waitForVisibility(element);

        element.clear();
        element.sendKeys(text);
    }

    protected String getText(By locator) {

        WebElement element = driver.findElement(locator);

        waitUtils.waitForVisibility(element);

        return element.getText();
    }

    protected boolean isDisplayed(By locator) {

        WebElement element = driver.findElement(locator);

        waitUtils.waitForVisibility(element);

        return element.isDisplayed();
    }
}