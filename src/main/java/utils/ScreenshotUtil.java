package utils;

import driver.DriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class ScreenshotUtil {

	private static final String SCREENSHOT_DIR = "test-output/extentreports/";

	public static String captureScreenshot(String name) throws IOException {
		String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
		String safeName = name.replaceAll("[^a-zA-Z0-9]", "_");
		String filename = safeName + "_" + timestamp + "_" + UUID.randomUUID() + ".png";

		// Ensure screenshot directory exists
		File screenshotDir = new File(SCREENSHOT_DIR);
		if (!screenshotDir.exists()) {
			screenshotDir.mkdirs();
		}

		// Final file path
		File destFile = new File(screenshotDir, filename);
		WebDriver driver = DriverManager.getDriver();
		File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		Files.copy(srcFile.toPath(), destFile.toPath());

		// Return absolute path (ExtentReports supports it)
		return destFile.getAbsolutePath();

	}

	public static void clearOldScreenshots() {
		File folder = new File("test-output/screenshots/");
		if (folder.exists()) {
			for (File file : folder.listFiles()) {
				if (file.isFile() && file.getName().endsWith(".png")) {
					file.delete();
				}
			}
		}
	}
}
