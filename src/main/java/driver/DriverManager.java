package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import config.ConfigReader;

public class DriverManager {
	private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

	private DriverManager() {
	} // prevent instantiation

	public static WebDriver getDriver() {
		return driver.get();
	}

	public static WebDriver getDriver(String browser) {
		if (driver.get() == null) {
			WebDriver driverInstance = createDriver(browser);
			driver.set(driverInstance);
		}
		return driver.get();
	}

	WebDriver driverInstance;

	private static WebDriver createDriver(String browser) {
		boolean headless = Boolean.parseBoolean(ConfigReader.getProperty("headless", "false"));

		WebDriver driverInstance;

		switch (browser.toLowerCase()) {
		case "chrome":
			System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
			ChromeOptions chromeOptions = new ChromeOptions();
			if (headless)
				chromeOptions.addArguments("--headless=new");
			driverInstance = new ChromeDriver(chromeOptions);
			break;

		case "firefox":
			System.setProperty("webdriver.gecko.driver", "drivers/geckodriver.exe");
			FirefoxOptions firefoxOptions = new FirefoxOptions();
			if (headless)
				firefoxOptions.addArguments("--headless");
			driverInstance = new FirefoxDriver(firefoxOptions);
			break;

		case "edge":
			System.setProperty("webdriver.edge.driver", "drivers/msedgedriver.exe");
			EdgeOptions edgeOptions = new EdgeOptions();
			if (headless)
				edgeOptions.addArguments("--headless");
			driverInstance = new EdgeDriver(edgeOptions);
			break;

		default:
			throw new IllegalArgumentException("Browser not supported: " + browser);
		}

		// Maximize window after launching
		driverInstance.manage().window().maximize();
		return driverInstance;

	}

	public static void quitDriver() {
		if (driver.get() != null) {
			driver.get().quit();
			driver.remove();
		}
	}
}
