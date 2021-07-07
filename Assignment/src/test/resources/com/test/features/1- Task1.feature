Feature: Verify that 100 rows are displayed on the page once user selects 100 from the show rows dropdown

@ValidateRows
Scenario: Verify that 100 rows are displayed on the page
Given Scenario Verify that 100 rows are displayed on the page
Given I open the page WEB.MAIN.URL
When I click on the link SHOW.ROWS.DROPDOWN
When I click on the link SHOW.ROWS.DROPDOWN.BUTTON.100
Then I validate the count of rows for table TABLE.BODY and TABLE.ROW.SERIAL