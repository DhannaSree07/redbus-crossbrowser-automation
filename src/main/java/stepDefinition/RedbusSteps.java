package stepDefinition;
import io.cucumber.java.en.*;
import java.time.LocalDate;

public class RedbusSteps extends BaseSteps {

	@Given("I am on the Redbus homepage")
	public void i_am_on_the_redbus_homepage() {
		// URL should be opened in Hooks, so this can be empty or check URL
	}

	@When("I select source city as {string}")
	public void i_select_source_city_as(String source) throws InterruptedException {
		home.searchBus(source);

	}

	@When("I select destination city as {string}")
	public void i_select_destination_city_as(String dest) {
		home.enterDestination(dest);
	}

	@When("I pick a travel date as {string}")
	public void i_pick_a_travel_date_as(String dateString) {
		LocalDate date = LocalDate.parse(dateString); // "2025-07-31"
		home.selectTravelDate(date);
	}

	@When("I click the Search button")
	public void i_click_the_search_button() {
		home.clickSearch();
	}

	@Then("I should see source and destination as {string} and {string}")
	public void i_should_see_source_and_destination_as_(String source, String destination) {
		results.validateRouteHeader(source, destination);
	}

	@Then("I should see the lowest priced bus with rating above {string}, type containing {string}, and time between {string} and {string}")
	public void i_should_see_the_lowest_priced_matching_bus_and_its_details(String rating, String type,
			String startHour, String endHour) {
		double ratingThreshold = Double.parseDouble(rating);
		int start = Integer.parseInt(startHour);
		int end = Integer.parseInt(endHour);
		results.filterLowestPriceBestBus(ratingThreshold, type, start, end);
	}

}