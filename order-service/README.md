# Order Service

Service to create orders.

Check with the `Catalog Service` if ordered phones exist in a database or the stock is greater than 0 (not yet implemented).

This should be implemented for phones in the order list and modify this stock when the order is OK and saved.

## Endpoints

+ `GET /order_api/order` - list of completed orders

+ `POST /order_api/order` - add a new order to a database
  + Request example json
    ```
    {
      "customerName":"Esteban",
      "customerSurname":"Martin",
      "email":"estebanmate@gmail.com",
      "orderDetails":[
        {
          "id":"1",
          "amount":2
        },
        {
          "id":"5",
          "amount":1
        }
      ]
    }
    ```
## App requirements
+ Spring Boot
+ Spring Framework
+ JDK 10
+ Docker

