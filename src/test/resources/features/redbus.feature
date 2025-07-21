@Practise
Feature: Redbus Booking Functionality
    
  Scenario Outline: User searches for buses and fills booking details
    Given I am on the Redbus homepage
    When I select source city as "<source>"
    And I select destination city as "<destination>"
    And I pick a travel date as "<travelDate>"
    And I click the Search button
    Then I should see source and destination as "<source>" and "<destination>"
		Then I should see the lowest priced bus with rating above "<rating>", type containing "<busType>", and time between "<startTime>" and "<endTime>"

   Examples:
  | source    | destination | travelDate  | rating | busType | startTime | endTime   |
  | Hyderabad | Bangalore   | 2025-07-31  | 3.5    | A/C     |  15       | 22        |
   
