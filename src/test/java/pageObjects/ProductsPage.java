package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductsPage extends BasePage {

    private By productsTitle =
            By.className("title");

    private By backpack =
            By.id("add-to-cart-sauce-labs-backpack");

    private By bikeLight =
            By.id("add-to-cart-sauce-labs-bike-light");

    private By cart =
            By.className("shopping_cart_link");

    private By backpackProduct =
            By.id("item_4_title_link");

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    public boolean isProductsPageDisplayed() {
        return isDisplayed(productsTitle);
    }

    public void addBackpackToCart() {
        click(backpack);
    }

    public void addBikeLightToCart() {
        click(bikeLight);
    }

    public void clickCart() {
        click(cart);
    }

    public void clickBackpackProduct() {
        click(backpackProduct);
    }
}