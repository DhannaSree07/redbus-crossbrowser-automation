package utils;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ReportingManager {
    private static final Map<String, ExtentReports> extentMap = new HashMap<>();
    private static final ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    public static ExtentReports getExtentReports(String browser) {
        String normalizedBrowser = browser.toLowerCase();

        if (!extentMap.containsKey(normalizedBrowser)) {
            String reportFilePath = getReportFilePath(normalizedBrowser);

            ExtentSparkReporter spark = new ExtentSparkReporter(reportFilePath);
            spark.config().setReportName("Redbus Booking Functional Test Report - " + normalizedBrowser);
            spark.config().setDocumentTitle("Redbus Automation");

            ExtentReports extent = new ExtentReports();
            extent.attachReporter(spark);
            extent.setSystemInfo("Browser", browser);

            extentMap.put(normalizedBrowser, extent);
        }

        return extentMap.get(normalizedBrowser);
    }

    private static String getReportFilePath(String browser) {
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String reportDir = "test-output/reports/" + browser.toLowerCase();
        new File(reportDir).mkdirs();  // Make sure folder exists
        return reportDir + "/ExtentReport_" + browser.toLowerCase() + "_" + timestamp + ".html";
    }

    public static void setTest(ExtentTest extentTest) {
        test.set(extentTest);
    }

    public static ExtentTest getTest() {
        return test.get();
    }

    public static void removeTest() {
        test.remove();
    }

    public static void flushReport() {
        for (ExtentReports extent : extentMap.values()) {
            extent.flush();
        }
    }
}

