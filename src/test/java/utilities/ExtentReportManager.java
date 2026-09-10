package utilities;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportManager {

    private static ExtentReports extent;

    // Stores one parent test for each test class
    private static Map<String, ExtentTest> classTests =
            new ConcurrentHashMap<>();

    private static String repName;

    // ============================================================
    // CREATE EXTENT REPORT
    // ============================================================

    public static void initializeReport() {

        if (extent != null) {
            return;
        }

        String timeStamp =
                new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss")
                        .format(new Date());

        repName = "AutomationReport_" + timeStamp + ".html";

        String reportPath =
                System.getProperty("user.dir")
                + "\\reports\\"
                + repName;

        File reportDirectory =
                new File(System.getProperty("user.dir") + "\\reports\\");

        if (!reportDirectory.exists()) {
            reportDirectory.mkdirs();
        }

        ExtentSparkReporter sparkReporter =
                new ExtentSparkReporter(reportPath);

        sparkReporter.config()
                .setDocumentTitle("CSPS Automation Report");

        sparkReporter.config()
                .setReportName("CSPS Automation Report");

        extent = new ExtentReports();

        extent.attachReporter(sparkReporter);

        // System information
        extent.setSystemInfo("Application", "CSPS");

        extent.setSystemInfo("Environment", "QA");

        extent.setSystemInfo("User Name", "Iswarya");

        extent.setSystemInfo(
                "Operating System",
                System.getProperty("os.name")
        );
    }

    // ============================================================
    // GET EXTENT
    // ============================================================

    public static ExtentReports getExtent() {

        if (extent == null) {
            initializeReport();
        }

        return extent;
    }

    // ============================================================
    // GET / CREATE CLASS TEST
    // ============================================================

    public static ExtentTest getClassTest(String className) {

        return classTests.computeIfAbsent(
                className,
                name -> getExtent().createTest(name)
        );
    }

    // ============================================================
    // FLUSH REPORT
    // ============================================================

    public static void flush() {

        if (extent != null) {
            extent.flush();
        }
    }
}