package commonActions;

import driver.DriverManager;

import java.time.Duration;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.IOException;
import utils.ScreenshotUtil;
import utils.ReportingManager;

public class CommonActions {

	protected WebDriver driver;
	protected WebDriverWait wait;
	private static final int DEFAULT_TIMEOUT = 15;

	// Accept WebDriver through constructor
	public CommonActions(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT));
	}

	public WebElement waitForVisibility1(By locator) {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	// Check if actual text equals expected
	public boolean checkEqualText(By locator, String expectedText) {
		String actualText = driver.findElement(locator).getText().trim();
		return actualText.equals(expectedText);
	}

	// Switch to frame by WebElement
	public void switchToFrame(WebElement frameElement) {
		driver.switchTo().frame(frameElement);
	}

	// Switch back to main content
	public void switchToMainWindow() {
		driver.switchTo().defaultContent();
	}

	// Check if checkbox or element is selected
	public boolean isChecked(WebElement element) {
		return element.isSelected();
	}

	// Check if element is enabled
	public boolean isEnabled(WebElement element) {
		return element.isEnabled();
	}

	// Save current window handle
	public String saveCurrentWindowHandle() {
		return driver.getWindowHandle();
	}

	// Select dropdown by visible text
	public void selectByText(WebElement dropdown, String visibleText) {
		new Select(dropdown).selectByVisibleText(visibleText);
	}

	// Select dropdown by value
	public void selectByValue(WebElement dropdown, String value) {
		new Select(dropdown).selectByValue(value);
	}

	// Select dropdown by index
	public void selectByIndex(WebElement dropdown, int index) {
		new Select(dropdown).selectByIndex(index);
	}

	// Accept alert
	public void acceptAlert() {
		try {
			Alert alert = driver.switchTo().alert();
			alert.accept();
		} catch (NoAlertPresentException e) {
			System.out.println("No alert present to accept.");
		}
	}

	// Dismiss alert
	public void dismissAlert() {
		try {
			Alert alert = driver.switchTo().alert();
			alert.dismiss();
		} catch (NoAlertPresentException e) {
			System.out.println("No alert present to dismiss.");
		}
	}

	// Scroll to element
	public void scrollToElement(WebElement element) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
	}

	// JavaScript click
	public void jsClick(By locator) {
		WebElement element = driver.findElement(locator);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
	}

	public void jsClick(By locator, int timeoutInSeconds) {
		WebDriverWait customWait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
		WebElement element = customWait.until(ExpectedConditions.presenceOfElementLocated(locator));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);

	}

	public void robustClick(By locator, int timeoutInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
		WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));

		try {
			element.click(); //
		} catch (Exception e1) {
			try {
				// Fallback to JavaScript
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
			} catch (Exception e2) {
				// Fallback to Actions class
				Actions actions = new Actions(driver);
				actions.moveToElement(element).click().build().perform();
			}
		}

	}

	public void actionsClick(By locator, int timeoutInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));

		Actions actions = new Actions(driver);
		actions.moveToElement(element).click().build().perform();
	}

	// Hover on element
	public void hoverOverElement(WebElement element) {
		Actions actions = new Actions(driver);
		actions.moveToElement(element).perform();
	}

	public void clearAndType(By locator, String text) {
		WebElement element = waitForVisibility(locator);
		element.click();
		element.sendKeys(text);
	}

	// Scroll by pixels
	public void scrollByPixels(int x, int y) {
		((JavascriptExecutor) driver).executeScript("window.scrollBy(" + x + "," + y + ")");
	}

	// Select dropdown by visible text
	public void selectByVisibleText(WebElement dropdown, String visibleText) {
		Select select = new Select(dropdown);
		select.selectByVisibleText(visibleText);
	}

	// Send keys with delay (for slower inputs)
	public void sendKeysSlowly(WebElement element, String text, long delayMillis) throws InterruptedException {
		for (char c : text.toCharArray()) {
			element.sendKeys(Character.toString(c));
			Thread.sleep(delayMillis);
		}
	}

	public static WebElement waitForVisibility(By locator) {
		WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(DEFAULT_TIMEOUT));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public WebElement waitForPresence(By locator, int timeoutInSeconds) {
		WebDriverWait customWait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
		return customWait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	public void jsEnterValue(By locator, String text) {
		WebElement element = driver.findElement(locator);
		waitForSeconds(2);
		((JavascriptExecutor) driver).executeScript(
				"arguments[0].value = arguments[1]; arguments[0].dispatchEvent(new Event('input'));", element, text);
	}

	public void jsEnterValue(By locator, String text, int timeoutInSeconds) {
		WebDriverWait customWait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
		WebElement element = customWait.until(ExpectedConditions.presenceOfElementLocated(locator));

		((JavascriptExecutor) driver).executeScript(
				"arguments[0].value = arguments[1]; arguments[0].dispatchEvent(new Event('input'));", element, text);
	}

	public void waitForSeconds(long seconds) {
		try {
			Thread.sleep(seconds * 1000); // Convert seconds to milliseconds
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			System.out.println("Thread interrupted during wait.");
		}
	}

	public void doubleClickAndType(By locator, String text) {
		WebElement element = driver.findElement(locator);
		Actions actions = new Actions(driver);

		actions.doubleClick(element).perform(); // double-click selects existing text
		element.sendKeys(Keys.BACK_SPACE); // delete selected text
		element.sendKeys(text); // type new value
	}

	public void clearWithJSAndType(By locator, String text) {
		WebElement element = driver.findElement(locator);

		// Clear the value using JavaScript
		((JavascriptExecutor) driver).executeScript("arguments[0].value='';", element);

		// Type the new text
		element.sendKeys(text);
	}

	// Logs screenshot using ExtentReport
	private void logScreenshot(String actionName) throws IOException {
		String path = ScreenshotUtil.captureScreenshot(actionName);
		ReportingManager.getTest().info("Action: " + actionName).addScreenCaptureFromPath(path);
	}

	public void captureAndLogScreenshot(String stepName) {
		try {
			String path = ScreenshotUtil.captureScreenshot(stepName);
			ReportingManager.getTest().info("Screenshot for: " + stepName).addScreenCaptureFromPath(path);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void takeScreenshot(String actionName) {
		captureAndLogScreenshot(actionName);
	}

}
