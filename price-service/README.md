##TABLE INFO:
SQL> show columns from product_price;
FIELD	TYPE		NULL	KEY	DEFAULT 
------------------------------------------------ 
PROD_ID	BIGINT(19)	NO	PRI	NULL
PRICE	BIGINT(19)	YES		NULL
TAX	INTEGER(10)	YES		NULL


##BUSINESS API's
// VIEW: To Get the Price for given a product id
GET 	<Hostname>:9191//meru/product/price/<Prod_ID>

	// To get the Price for given product id 1001
	Ex: localhost//meru/product/price/1001
	GET <Hostname>:9191//meru/product/price/<Prod_ID>
	output JSON:
	{
		"prodId": 1005,
		"price": 3000,
		"tax": 12
	}
STATUS: 200 OK

// CREATE: To Create Pricing Record 
POST 	<Hostname>:9191//meru/product/price

	// To get the Price for given product id 1001
	POST <Hostname>:9191//meru/product/price
	Input JSON:
	{
		"prodId": 1001,
		"price": 3000,
		"tax": 12
	}
STATUS: 200 OK

// MODIFY: To Modify Pricing Record 
POST 	<Hostname>:9191//meru/product/price/<Prod_ID>

	// To get the Price for given product id 1001
	POST <Hostname>:9191//meru/product/price/1001
	Input JSON:
	{
		"prodId": 1001,
		"price": 3999,
		"tax": 8
	}
STATUS: 200 OK

// DELETE: To Get the Price for given a product id
DELETE 	<Hostname>:9191//meru/product/price/<Prod_ID>

	// To get the Price for given product id 1004
	Ex: localhost//meru/product/price/1004
	GET <Hostname>:9191//meru/product/price/1004
STATUS: 200 OK


ERROR:
{
    "timestamp": "2020-09-20T10:12:24.651+00:00",
    "message": "Product id-1005",
    "details": "uri=//meru/product/price/1005"
}