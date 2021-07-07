Feature: Verify correct data is displayed after applying filters

@ValidateFilters
Scenario: Validate Filters
Given Scenario Validate Filters
Given I open the page WEB.MAIN.URL
When I click on the link FILTER.BUTTON
When I click on the link ADD.FILTER.BUTTON
When I click on the link MARKET.CAP.BUTTON
When I click on the link ONE.TEN.BILLION.RANGE.BUTTON
When I click on the link APPLY.FILTER.BUTTON
When I click on the link SHOW.RESULTS.BUTTON
Then I validate the TABLE.BODY by using MARKET.CAP.VALUE in between ONE.BILLION TEN.BILLION