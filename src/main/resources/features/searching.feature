Feature: Test search on ebay

  Background: Non-registered on ebay page not
    Given I am a "non-registered" customer
    And I navigate to "www.ebay.co.uk"

  Scenario: Search and verify results
    When I search for an item "bill"
    Then I get a list of matching results
    And the resulting items cards show: price, postage price, No of bids or show Buy it now
    When I can sort the results by "Lowest price"
    Then the results are listed in the page in the correct order
    When I can sort the results by "Highest price"
    Then the results are listed in the page in the correct order
    When I can filter the results by "Buy it now"
    Then all the results shown in the page have the "Buy it now" tag

  Scenario: Search per category
    When I enter a search term "bill" and select a specific Category "Coins"
    Then I get a list of matching results
    And I can verify that the results shown as per the selected category

  Scenario: Search and navigate through results pages
    When I search for an item "bill"
    Then I get a list of matching results
    And the results show more than one page
    Then the user can navigate through the pages to continue looking at the items



