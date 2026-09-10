package testCases;

import org.testng.annotations.Test;

import pageObjects.LoginPage;
import pageObjects.ProductsPage;
import testBase.BaseClass;
import utilities.ConfigReader;
import utilities.ReportLogger;

public class LoginTest extends BaseClass {

    @Test(groups = {"smoke"})
    public void validateLogin() {

        ReportLogger.info(log, "Start test case");

        LoginPage loginPage =
                new LoginPage(driver);

        loginPage.enterUsername(
                ConfigReader.getProperty("username"));

        loginPage.enterPassword(
                ConfigReader.getProperty("password"));

        ReportLogger.info(
                log,
                "Entered username and password");

        loginPage.clickLogin();

        ReportLogger.info(
                log,
                "Clicked on login button");

        ProductsPage productsPage =
                new ProductsPage(driver);

        assert productsPage.isProductsPageDisplayed();

        ReportLogger.pass(
                log,
                "Login test passed");
    }
}