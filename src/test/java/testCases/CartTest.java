package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.CartPage;
import pageObjects.LoginPage;
import pageObjects.ProductsPage;
import testBase.BaseClass;
import utilities.ConfigReader;

public class CartTest extends BaseClass {

    @Test(groups = {"regression"})
    public void removeProductFromCartTest() {

        LoginPage loginPage =
                new LoginPage(driver);

        loginPage.enterUsername(
                ConfigReader.getProperty("username"));

        loginPage.enterPassword(
                ConfigReader.getProperty("password"));

        loginPage.clickLogin();
        
        

        ProductsPage productsPage =
                new ProductsPage(driver);

        productsPage.addBackpackToCart();
        productsPage.clickCart();

        CartPage cartPage =
                new CartPage(driver);

        Assert.assertTrue(
                cartPage.isBackpackDisplayed());

        cartPage.removeBackpack();

        Assert.assertFalse(
                cartPage.isBackpackDisplayed());
    }
}