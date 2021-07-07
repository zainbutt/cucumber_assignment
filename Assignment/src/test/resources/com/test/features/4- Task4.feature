Feature: validate API response

@ValidateLogoURL
Scenario: Confirm that the following logo URL is present: “logo”: https://s2.coinmarketcap.com/static/img/coins/64x64/1027.png
Given Scenario Confirm that the following logo URL is present: logo: https://s2.coinmarketcap.com/static/img/coins/64x64/1027.png
Then AssertEquals JSON.LOGO.URL with expected EXPECTED.JSON.URL

@ValidateTechnicalDocURL
Scenario: Confirm that the technical_doc Url is present: technical_doc: [https://github.com/thereum/wiki/wiki/White-Paper]
Given Scenario Confirm that the technical_doc Url is present: technical_doc: [https://github.com/thereum/wiki/wiki/White-Paper]
Then AssertEquals TECHNICAL.JSON.URL with expected EXPECTED.TECHNICAL.JSON.URL

@ValidateCurrencySymbol
Scenario: Confirm that the symbol of the currency is ETH: “symbol”: “ETH”
Given Scenario Confirm that the symbol of the currency is ETH: “symbol”: “ETH”
Then AssertEquals JSON.CURRENCY.SYMBOL with expected EXPECTED.JSON.CURRENCY.SYMBOL

@ValidateDateAdded
Scenario: Confirm that the date added is: date_added
Given Scenario Confirm that the date added is: date_added
Then AssertEquals JSON.DATE.ADDED with expected EXPECTED.JSON.DATE.ADDED

@ValidateMineableTag
Scenario: Confirm that the currency has the mineable tag associated with it:tags: [ mineable ]
Given Scenario Confirm that the currency has the mineable tag associated with it:tags: [ mineable ]
Then AssertTrue JSON.MINEABLE.TAG with expected EXPECTED.JSON.MINEABLE.TAG