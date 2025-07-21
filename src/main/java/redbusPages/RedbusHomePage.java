package redbusPages;

import driver.DriverManager;
import utils.ReportingManager;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.Status;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import commonActions.CommonActions;

public class RedbusHomePage extends CommonActions {

	WebDriver driver = DriverManager.getDriver();

	public By srcField = By.xpath("//div[@role='button' and .//div[text()='From']]");
	public By srcDestInput = By.xpath("//div[contains(@class,'inputWrapper')]//input");
	public By destField = By.xpath("//div[contains(@class, 'labelCityWrapper') and .//div[text()='To']]");
	public By destInput = By.id("srcDest");
	public By searchButton = By.xpath("//button[contains(@class,'searchButtonWrapper')]");
	public By clickCalendar = By.xpath("//i[contains(@class,'icon icon-date_range')]");
	public By selectMonth = By.xpath("//div[contains(@class,'monthYear')]");
	public By nextArrow = By.xpath("//*[contains(@class,'mainDatesWrap')]//i[contains(@class,'right')]");
	public By selectDate = By.xpath("//ul[contains(@class,'datesWrap')]//div[@data-datetype='AVAILABLE']//span");

	public RedbusHomePage(WebDriver driver) {
		super(driver);
	}

	public By getCityOptionByText(String city) {
		String xpath = "//div[contains(@class, 'searchCategory')]//div[contains(@class, 'listHeader') and text()='"
				+ city + "']";
		return By.xpath(xpath);
	}

	public void searchBus(String fromCity) {
		jsClick(srcField, 5);
		jsEnterValue(srcDestInput, fromCity, 2);
		clearWithJSAndType(srcDestInput, fromCity);
		jsClick(getCityOptionByText(fromCity), 2);
		ReportingManager.getTest().log(Status.INFO, "Selected From city: " + fromCity);

	}

	public void enterDestination(String destination) {
		jsClick(destField, 3);
		jsEnterValue(destInput, destination, 2);
		clearWithJSAndType(destInput, destination);
		jsClick(getCityOptionByText(destination), 2);
		ReportingManager.getTest().log(Status.INFO, "Selected Destination city: " + destination);

	}

	public void selectDate(String expectedMonthYear, String day) {

		jsClick(clickCalendar, 2);

		while (true) {
			// Get current month + year displayed on the calendar
			WebElement monthElement = waitForPresence(selectMonth, 10);
			String currentMonthYear = monthElement.getText();

			if (currentMonthYear.equalsIgnoreCase(expectedMonthYear)) {
				break;
			}

			// Click next arrow
			WebElement clickNextArrow = waitForPresence(nextArrow, 10);
			clickNextArrow.click();
		}

		// Once desired month is displayed, click on the day
		List<WebElement> allDates = driver.findElements(selectDate);

		for (WebElement date : allDates) {
			if (date.getText().equals(day)) {
				date.click();
				break;
			}
		}
	}

	public void selectTravelDate(LocalDate targetDate) {
		String expectedMonthYear = targetDate.format(DateTimeFormatter.ofPattern("MMMM yyyy")); // July 2025
		String day = String.valueOf(targetDate.getDayOfMonth()); // 31
		selectDate(expectedMonthYear, day);
		ReportingManager.getTest().log(Status.INFO, "Selected the Travel Date: " + targetDate);
		takeScreenshot("After Filling Travel Details");
	}

	public void clickSearch() {
		robustClick(searchButton, 4);
		ReportingManager.getTest().log(Status.INFO, "Clicked on the search buses");

	}
}
