package redbusPages;

import driver.DriverManager;
import utils.ReportingManager;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.aventstack.extentreports.Status;

import commonActions.CommonActions;

public class SearchResultsPage extends CommonActions {

	WebDriver driver = DriverManager.getDriver();
	public By allPriceElements = By
			.xpath("//div[contains(@class,'invWrap__ind-search')]//following::p[contains(@class,'finalFare')]");
	public By validateHeading = By.xpath("//*[contains(@class,'breadCrumbLink')]");
	By routeHeader = By.xpath("//div[contains(@class,'twoLineTitle')]");

	public SearchResultsPage(WebDriver driver) {
		super(driver);
	}

	public void validateRouteHeader(String expectedSource, String expectedDestination) {

		waitForPresence(validateHeading, 20);
		WebElement breadcrumbElement = driver.findElement(validateHeading);

		// Get the visible text
		String breadcrumbText = breadcrumbElement.getText().toLowerCase(); // Case-insensitive comparison

		// Assert both cities are present
		Assert.assertTrue(breadcrumbText.contains(expectedSource.toLowerCase()),
				"Source city not found in breadcrumb: " + expectedSource);

		Assert.assertTrue(breadcrumbText.contains(expectedDestination.toLowerCase()),
				"Destination city not found in breadcrumb: " + expectedDestination);

		ReportingManager.getTest().log(Status.INFO, "Validated source and destination successfully " + breadcrumbText);
	}

	public Map<String, String> getFilteredBusDetails(double ratingThreshold, String typeKeyword, int startHour,
			int endHour) {
		waitForPresence(allPriceElements, 50);
		System.out.println("Fetching details...");

		List<WebElement> names = driver.findElements(By.xpath("//div[contains(@class,'travelsName')]"));
		List<WebElement> times = driver.findElements(By.xpath("//div[contains(@class,'timeRow')]"));
		List<WebElement> types = driver.findElements(By.xpath("//p[contains(@class,'busType')]"));
		List<WebElement> ratings = driver.findElements(By.xpath("//*[contains(@class,'rating___')]"));
		List<WebElement> prices = driver.findElements(
				By.xpath("//div[contains(@class,'invWrap__ind-search')]//following::p[contains(@class,'finalFare')]"));

		int lowestPrice = Integer.MAX_VALUE;
		int bestIndex = -1;

		for (int i = 0; i < prices.size(); i++) {
			try {
				double rating = Double.parseDouble(ratings.get(i).getText().trim());
				String type = types.get(i).getText().trim();

				String fullTime = times.get(i).getText().trim();
				String departureTime = fullTime.split("\\n")[0].trim(); // extract only departure time
				int hour = convertTo24HourFormat(departureTime);

				if (rating > ratingThreshold && type.contains(typeKeyword) && hour >= startHour && hour <= endHour) {
					String priceText = prices.get(i).getText().replaceAll("[^0-9]", "");
					int price = Integer.parseInt(priceText);
					if (price < lowestPrice) {
						lowestPrice = price;
						bestIndex = i;
					}
				}
			} catch (Exception e) {
				System.out.println("Skipping index " + i + ": " + e.getMessage());
			}
		}

		if (bestIndex != -1) {
			Map<String, String> details = new LinkedHashMap<>();
			details.put("Bus Name", names.get(bestIndex).getText());
			details.put("Type", types.get(bestIndex).getText());
			details.put("Time", times.get(bestIndex).getText());
			details.put("Rating", ratings.get(bestIndex).getText());
			details.put("Price", prices.get(bestIndex).getText());

			WebElement target = prices.get(bestIndex);
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", target);
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", target);

			return details;
		} else {
			throw new RuntimeException("❌ No buses matched the filter criteria.");
		}
	}

	public int convertTo24HourFormat(String time) {
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("H:mm"); // handles 6:15 or 06:15 or 23:15
			LocalTime localTime = LocalTime.parse(time.trim(), formatter);
			return localTime.getHour(); // returns 23 for "23:30"
		} catch (Exception e) {
			throw new RuntimeException("Invalid time format: " + time);
		}
	}

	public void filterLowestPriceBestBus(double ratingThreshold, String typeKeyword, int startHour, int endHour) {
		waitForPresence(allPriceElements, 50);
		Map<String, String> result = getFilteredBusDetails(ratingThreshold, typeKeyword, startHour, endHour);

		if (result != null) {
			System.out.println("Lowest Priced Matching Bus:");
			result.forEach((key, value) -> System.out.println(key + ": " + value));

			Assert.assertTrue(result.containsKey("Price"));
			Assert.assertTrue(result.containsKey("Bus Name"));

			String busName = result.getOrDefault("Bus Name", "N/A");
			String price = result.getOrDefault("Price", "N/A");
			String departureTime = result.getOrDefault("Departure", "N/A");
			String rating = result.getOrDefault("Rating", "N/A");
			String busType = result.getOrDefault("Type", "N/A");

			String logMessage = String.format(
					"🚌 Best Bus Selected: <b>%s</b> | 🕒 Departure: %s | 💰 Price: %s | ⭐ Rating: %s | 🛏️ Type: %s",
					busName, departureTime, price, rating, busType);

			ReportingManager.getTest().log(Status.INFO, logMessage);

		} else {
			Assert.fail("No matching bus details available.");
		}

	}

}
