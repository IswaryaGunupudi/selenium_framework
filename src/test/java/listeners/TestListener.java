package listeners;

import java.lang.reflect.Field;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import testBase.BaseClass;
import utilities.CaptureScreen;
import utilities.ExtentReportManager;

public class TestListener implements ITestListener {

    private static ThreadLocal<ExtentTest> test =
            new ThreadLocal<>();

    public static ExtentTest getTest() {
        return test.get();
    }

    @Override
    public void onTestStart(ITestResult result) {

        String className = result.getTestClass()
                .getRealClass()
                .getSimpleName();

        // Create parent class
        ExtentTest classTest =
                ExtentReportManager.getClassTest(className);

        // Create test method under class
        ExtentTest methodTest =
                classTest.createNode(
                        result.getMethod().getMethodName());

        // Assign groups
        methodTest.assignCategory(
                result.getMethod().getGroups());

        // Store current test
        test.set(methodTest);

        test.get().log(
                Status.INFO,
                "Test execution started");
    }

    @Override
    public void onTestSuccess(ITestResult result) {

        if (test.get() != null) {

            test.get().log(
                    Status.PASS,
                    result.getMethod().getMethodName()
                    + " got successfully executed");
        }

        test.remove();
    }

    @Override
    public void onTestFailure(ITestResult result) {

        if (test.get() == null) {
            return;
        }

        test.get().fail(result.getThrowable());

        try {

            // Get test class instance
            Object testClass = result.getInstance();

            // Get driver field from test class
            Field driverField = result.getTestClass()
                    .getRealClass()
                    .getDeclaredField("driver");

            driverField.setAccessible(true);

            WebDriver driver =
                    (WebDriver) driverField.get(testClass);

            String testName =
                    result.getMethod().getMethodName();

            // Capture screenshot
            CaptureScreen captureScreen =
                    new CaptureScreen();

            String screenshotPath =
                    captureScreen.captureScreen(
                            driver,
                            testName);

            // Convert screenshot to Base64
            String base64 =
                    captureScreen.convertToBase64(
                            screenshotPath);

            if (base64 != null) {

                String img =
                        "<img src='data:image/png;base64,"
                        + base64
                        + "' style='width:400px;"
                        + "cursor:pointer;"
                        + "border:1px solid #ccc;' "
                        + "onclick='this.nextElementSibling."
                        + "style.display=\"flex\"'/>"

                        + "<div style='display:none;"
                        + "position:fixed;"
                        + "top:0;left:0;width:100%;"
                        + "height:100%;"
                        + "background:rgba(0,0,0,0.8);"
                        + "justify-content:center;"
                        + "align-items:center;' "
                        + "onclick='this.style.display=\"none\"'>"

                        + "<img src='data:image/png;base64,"
                        + base64
                        + "' style='max-width:90%;"
                        + "max-height:90%;"
                        + "border:2px solid white;'/>"

                        + "</div>";

                test.get().fail(
                        "Failure Screenshot:<br>" + img);
            }

        } catch (Exception e) {

            test.get().fail(
                    "Could not capture screenshot: "
                    + e.getMessage());
        }

        test.remove();
    }

    @Override
    public void onTestSkipped(ITestResult result) {

        if (test.get() != null) {

            test.get().log(
                    Status.SKIP,
                    result.getMethod().getMethodName()
                    + " got skipped");

            if (result.getThrowable() != null) {

                test.get().log(
                        Status.INFO,
                        "Skip reason: "
                        + result.getThrowable().getMessage());
            }
        }

        test.remove();
    }

    @Override
    public void onFinish(ITestContext context) {

        ExtentReportManager.flush();

        test.remove();
    }
}