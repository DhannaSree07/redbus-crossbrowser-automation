package stepDefinition;

import org.openqa.selenium.WebDriver;

import driver.DriverManager;
import redbusPages.RedbusHomePage;
import redbusPages.SearchResultsPage;

public class BaseSteps {
	protected WebDriver driver;
	protected RedbusHomePage home;
	protected SearchResultsPage results;

	public BaseSteps() {
		// this.driver = Hooks.driver; // driver is already initialized in Hooks
		this.driver = DriverManager.getDriver();
		this.home = new RedbusHomePage(driver);
		this.results = new SearchResultsPage(driver);

	}
}
