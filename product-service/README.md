# APPLICATION INFO

Service : `Product Service`

Application Name: `product-service`

TABLE INFO:

database: `H2`

datasource:`productdb`
                    
###Table1: 

`CATEGORY'                 
| field name  | type | 
| -----------| ------
| categoryid  | int | 
| categoryname| string | 
| description | string | 

###Table2

 `''  

`PRODUCT'                 
| field name  | type| 
| ---------- -| -------| 
| productid   | int| 
| productname | string| 
|description | string| 
| category   |  CATEGORY| 


###BUSINESS API's

'Category APIs'

1) ADD Category ( Create a product Category)

`POST 	<Hostname>:8010//addCategory`
    -request body 	{
		"categoryid": 100,
		"categoryname": "cName1",
		"description": "cDes1"
	}
- ResponseCode: 201
	


2) VIEW Category ( View a Category)

`GET 	<Hostname>:8010//category/{categoryID}`
- ResponseCode: 200


3)LIST Category ( List all Categories)

`GET 	<Hostname>:8010//categories`
- ResponseCode: 200


4)MODIFY Category ( Modify Category)
`POST 	<Hostname>:8010//category`
    -request body 	{
		"categoryid": 100,
		"categoryname": "cName1Modified",
		"description": "cDes1Modified"
	}


'Product APIs'

1) ADD Product ( Create a product)

`POST 	<Hostname>:8010//addProduct`
	- Request body 	{
		"productid": 200,
		"productname": "pName1",
		"description": "pDes1",
		"category":{
		    "categoryid":100
		}
	}
- ResponseCode : 200


2) VIEW Product ( View a Product)

`GET 	<Hostname>:8010//product/{productID}`
- ResponseCode: 200
- Response Body:
{
		"productid": 200,
		"productname": "pName1",
		"description": "pDes1",
		"categoryname": "cName"
}


3)LIST Product ( List all Products)

`GET 	<Hostname>:8010//products`
- ResponseCode: 200
- Response Body:
[
    {
		"productid": 100,
		"productname": "pName1",
		"description": "pDes1",
		"category": "cName"
	},   
	{
		"productid": 101,
		"productname": "pName2",
		"description": "pDes2",
		"category": "cName001"
	}
    ]


4)MODIFY Product ( Modify product)

`POST 	<Hostname>:8010//category`
    -request body 	{
		"categoryid": 200,
		"categoryname": "pName1Modified",
		"description": "cDes1Modified"
	}
- Response Code: 200
- Response Body:  
        {
		"productid": 100,
		"productname": "pName1",
		"description": "pDes1",
		"categoryname": "cName"
		}


'ERROR:'
```{
    "errorMessage": "No value present",
    "requestedURI": "/product/100001"
}```
