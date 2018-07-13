# Catalog Service

Service to store phones data.

## Endpoints

+ `GET /catalog_api/phone` - list of available phones

+ `GET /catalog_api/phone?phoneId={phoneId}` - list of phones included in the comma-separated phone id list

+ `POST /catalog_api/phone` - add a new phone to the database
  + Request example json
    ```
    {
        "id": "1",
        "name": "NOKIA 10",
        "description": "LG 10 description",
        "catalogImage": "https://imagestore.com/lg-image.png",
        "price": 2600.98
		"stock": 2
    }
    ```

## App requirements
+ Spring Boot
+ Spring Framework
+ JDK 10
+ Docker

