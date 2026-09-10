package utilities;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class CaptureScreen {

    // ============================================================
    // CAPTURE SCREENSHOT
    // ============================================================

    public String captureScreen(WebDriver driver, String testName)
            throws IOException {

        String timeStamp =
                new SimpleDateFormat("yyyyMMddHHmmss")
                        .format(new Date());

        // Screenshot folder
        String directoryPath =
                System.getProperty("user.dir") + "\\screenshots\\";

        File directory = new File(directoryPath);

        if (!directory.exists()) {
            directory.mkdirs();
        }

        // Safe test name
        String safeTestName =
                testName.replaceAll("[^a-zA-Z0-9._-]", "_");

        String targetFilePath =
                directoryPath
                + safeTestName
                + "_"
                + timeStamp
                + ".png";

        // ========================================================
        // TAKE SCREENSHOT
        // ========================================================

        TakesScreenshot ts = (TakesScreenshot) driver;

        File source =
                ts.getScreenshotAs(OutputType.FILE);

        File target =
                new File(targetFilePath);

        Files.copy(
                source.toPath(),
                target.toPath()
        );

        return targetFilePath;
    }

    // ============================================================
    // CONVERT SCREENSHOT TO BASE64
    // ============================================================

    public String convertToBase64(String screenshotPath)
            throws IOException {

        byte[] fileContent =
                Files.readAllBytes(
                        new File(screenshotPath).toPath()
                );

        return Base64.getEncoder()
                .encodeToString(fileContent);
    }
}