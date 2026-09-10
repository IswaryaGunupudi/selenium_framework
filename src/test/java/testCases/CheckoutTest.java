package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.CartPage;
import pageObjects.CheckoutPage;
import pageObjects.LoginPage;
import pageObjects.ProductsPage;
import testBase.BaseClass;
import utilities.ConfigReader;

public class CheckoutTest extends BaseClass {

    @Test(groups = {"smoke", "regression"})
    public void checkoutTest() {

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

        cartPage.clickCheckout();

        CheckoutPage checkoutPage =
                new CheckoutPage(driver);

        checkoutPage.enterFirstName("Iswarya");
        checkoutPage.enterLastName("Test");
        checkoutPage.enterPostalCode("500001");

        checkoutPage.clickContinue();

        checkoutPage.clickFinish();

        Assert.assertTrue(
                checkoutPage.isOrderCompleted());
    }
}