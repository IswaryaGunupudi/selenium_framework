package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage extends BasePage {

    private By cartTitle =
            By.className("title");

    private By backpack =
            By.xpath("//div[text()='Sauce Labs Backpack']");

    private By removeBackpack =
            By.id("remove-sauce-labs-backpack");

    private By checkout =
            By.xpath("//button[text()='Checkout']");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public boolean isCartPageDisplayed() {
        return isDisplayed(cartTitle);
    }

    public boolean isBackpackDisplayed() {
        return isDisplayed(backpack);
    }

    public void removeBackpack() {
        click(removeBackpack);
    }

    public void clickCheckout() {
        click(checkout);
    }
}