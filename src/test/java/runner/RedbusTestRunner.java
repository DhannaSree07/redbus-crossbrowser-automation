package runner;

import driver.DriverManager;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.*;
import utils.ReportingManager;
import utils.RuntimeConfig;

@CucumberOptions(features = "src/test/resources/features/redbus.feature", glue = { "stepDefinition",
		"hooks" }, tags = "@Practise", plugin = { "pretty", "html:target/cucumber-reports.html",
				"json:target/cucumber-report.json" }, dryRun = false, monochrome = true)
public class RedbusTestRunner extends AbstractTestNGCucumberTests {

	private static final Logger logger = LogManager.getLogger(RedbusTestRunner.class);

	@Parameters("browser")
	@BeforeTest(alwaysRun = true)
	public void setBrowser(@Optional("chrome") String browser) {
		System.setProperty("browser", browser);
		RuntimeConfig.setBrowser(browser);
		logger.info("✔ [BeforeTest] Set browser in system & RuntimeConfig: " + browser);
	}

	@BeforeClass(alwaysRun = true)
	public void beforeClassLogging() {
		logger.info("=============== Test Execution Started ===============");
		logger.info("Extent report instance will be created dynamically per browser.");
	}

	@AfterClass(alwaysRun = true)
	public void teardown() {
		logger.info("=============== Test Execution Finished ===============");
		ReportingManager.flushReport();
		DriverManager.quitDriver();
		RuntimeConfig.clear();
		logger.info("✔ Driver closed & RuntimeConfig cleared");
	}
}
