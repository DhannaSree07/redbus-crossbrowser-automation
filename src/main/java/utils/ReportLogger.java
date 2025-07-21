package utils;

import com.aventstack.extentreports.Status;
import java.io.IOException;

public class ReportLogger {

	/**
	 * Logs a step with a screenshot to the ExtentReport.
	 * 
	 * @param message Descriptive step log to appear in report
	 */
	public static void logStepWithScreenshot(String message) {
		try {
			// Capture and return the screenshot path
			String screenshotPath = ScreenshotUtil.captureScreenshot(message.replaceAll(" ", "_"));

			// Log with screenshot
			ReportingManager.getTest().log(Status.INFO, message).addScreenCaptureFromPath(screenshotPath);
		} catch (IOException e) {
			ReportingManager.getTest().warning("Could not attach screenshot for step: " + message);
			e.printStackTrace();
		}
	}
}
