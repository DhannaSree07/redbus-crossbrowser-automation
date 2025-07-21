package hooks;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import driver.DriverManager;
import io.cucumber.java.*;
import org.openqa.selenium.WebDriver;
import utils.ReportingManager;
import utils.RuntimeConfig;
import utils.ScreenshotUtil;
import config.ConfigReader;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.io.IOException;

public class ExtentHooks {

	private static final Logger logger = LogManager.getLogger(ExtentHooks.class);

	@Before(order = 0)
	public void clearScreenshotsBeforeSuite() {
		ScreenshotUtil.clearOldScreenshots();
	}

	@Before
	public void beforeScenario(Scenario scenario) {
		try {
			String browser = RuntimeConfig.getBrowser();
			System.out.println("Scenario Started on Browser: " + browser);

			if (browser == null || browser.isEmpty()) {
				throw new RuntimeException(
						"Browser is null or empty — was it passed via testng.xml or set in RuntimeConfig?");
			}

			ExtentReports extent = ReportingManager.getExtentReports(browser);
			ExtentTest test = extent.createTest(scenario.getName());
			ReportingManager.setTest(test);
			test.assignCategory(browser);

			WebDriver driver = DriverManager.getDriver(browser);
			driver.manage().window().maximize();
			driver.get(ConfigReader.getProperty("url"));
			test.log(Status.INFO, "Launched browser and navigated to URL");

		} catch (Exception e) {
			System.out.println("Exception in @Before Hook: " + e.getMessage());
			e.printStackTrace();
			throw e;
		}
	}

	// Runs after each step - attach screenshot on failure
	@AfterStep
	public void afterEachStep(Scenario scenario) throws IOException {
		if (scenario.isFailed()) {
			String screenshotPath = ScreenshotUtil.captureScreenshot("FailedStep_" + scenario.getName());
			ReportingManager.getTest().fail("Step Failed - Screenshot").addScreenCaptureFromPath(screenshotPath);
		}
	}

	// Runs after each scenario
	@After
	public void afterScenario(Scenario scenario) {
		try {
			// Final screenshot
			String screenshotPath = ScreenshotUtil.captureScreenshot("Final_" + scenario.getName());
			ReportingManager.getTest().info("Final Screenshot").addScreenCaptureFromPath(screenshotPath);

			// Log status
			if (scenario.isFailed()) {
				ReportingManager.getTest().fail("Scenario Failed");
			} else {
				ReportingManager.getTest().pass("Scenario Passed");
			}

		} catch (IOException e) {
			e.printStackTrace();
			ReportingManager.getTest().warning("Could not attach final screenshot");
		} finally {
			ReportingManager.flushReport();
			ReportingManager.removeTest();
			DriverManager.quitDriver();
		}
	}
}
