package Utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Screenshoot {
    private ExtentReports extent;
    private ExtentTest test;

    public void capture(WebDriver driver) throws IOException {
//        extent = new ExtentReports();
//        ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter("extent-reports/report.html");
//        extent.attachReporter(extentSparkReporter);

        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String screenshotPath = "extent-reports/screenshots/" + System.currentTimeMillis() + ".png";
        Files.copy(screenshot.toPath(), Paths.get(screenshotPath));
        test.addScreenCaptureFromPath(screenshotPath);
    }
}
