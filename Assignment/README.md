Tools, Technologies and Frameworks used in the Assignment are as below
- Selenium Webdriver
- Java - 8
- Junit
- RestAssured
- Cucumber - BDD framework
- Singleton Design Pattern
- Maven
- Extent Reporting

Below are the steps to execute the assignment
- Download the project from GIT repo (https://github.com/zainbutt/cucumber_assignment.git)
- Go to the project directory
- Execute below maven command
mvn test -Dcucumber.options="--tags '@ValidateRows,@ValidateFilters,@ValidateLogoURL,@ValidateTechnicalDocURL,@ValidateCurrencySymbol,@ValidateDateAdded,@ValidateMineableTag'"

- To view the report Go to the project directory --> target --> cucumber-reports --> report.html
