Feature: Retrieve the ID of bitcoin (BTC), usd tether (USDT), and Ethereum (ETH), using the /cryptocurrency/map call and then convert them to Bolivian
Boliviano,

@ConvertPrice
Scenario Outline: Retrive IDs and convert them to Bolivian
Given User converts the price using below data <ID_KEY> <CURRENCY> <JSON_ID> <JSON_AMOUNT_KEY> <JSON_AMOUNT> <JSON_CONVERT_ID> <CONVERTER_ID>

Examples:
|ID_KEY		|CURRENCY				|JSON_ID		|JSON_AMOUNT_KEY		|JSON_AMOUNT				|JSON_CONVERT_ID	|CONVERTER_ID	|
|ID.KEY		|CURRENCY.BTC		|JSON.ID		|JSON.AMOUNT.KEY		|JSON.AMOUNT.VALUE	|JSON.CONVERT.ID	|CONVERT_TO_ID|
|ID.KEY		|CURRENCY.USDT	|JSON.ID		|JSON.AMOUNT.KEY		|JSON.AMOUNT.VALUE	|JSON.CONVERT.ID	|CONVERT_TO_ID|
|ID.KEY		|CURRENCY.ETH		|JSON.ID		|JSON.AMOUNT.KEY		|JSON.AMOUNT.VALUE	|JSON.CONVERT.ID	|CONVERT_TO_ID|