package utilities;

import org.apache.logging.log4j.Logger;

import listeners.TestListener;
import com.aventstack.extentreports.Status;

public class ReportLogger {

    public static void info(Logger log, String message) {

        // Log4j
        if (log != null) {
            log.info(message);
        }

        // Extent Report
        if (TestListener.getTest() != null) {
            TestListener.getTest()
                    .log(Status.INFO, message);
        }
    }

    public static void pass(Logger log, String message) {

        // Log4j
        if (log != null) {
            log.info(message);
        }

        // Extent Report
        if (TestListener.getTest() != null) {
            TestListener.getTest()
                    .log(Status.PASS, message);
        }
    }

    public static void fail(Logger log, String message) {

        // Log4j
        if (log != null) {
            log.error(message);
        }

        // Extent Report
        if (TestListener.getTest() != null) {
            TestListener.getTest()
                    .log(Status.FAIL, message);
        }
    }

    public static void warning(Logger log, String message) {

        // Log4j
        if (log != null) {
            log.warn(message);
        }

        // Extent Report
        if (TestListener.getTest() != null) {
            TestListener.getTest()
                    .log(Status.WARNING, message);
        }
    }
}