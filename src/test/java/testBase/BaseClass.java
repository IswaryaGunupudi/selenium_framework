package testBase;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.apache.logging.log4j.LogManager;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import utilities.ConfigReader;
import utilities.DriverFactory;

public class BaseClass {

    protected WebDriver driver;
    public Logger log;

    @BeforeClass
    public void setup() {

        log = LogManager.getLogger(this.getClass());

        DriverFactory.initDriver();

        driver = DriverFactory.getDriver();

        driver.get(ConfigReader.getProperty("url"));
    }

    @AfterClass
    public void tearDown() {

        DriverFactory.quitDriver();
    }
}