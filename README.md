# Phone App
Phone catalog and order application based on microservices and docker.

# Microservices
+ catalog-service (to store and list phone data and check available phones)
+ order-service (to add new orders and list completed orders)

Each service handles it's own database (containerized MongoDB).

## Relationship
**order-service** calls **catalog-service** to verify if phones are available (stock control not yet implemented) and to retrieve phone list metadata (name, price)

## App requirements
+ Spring Boot
+ Spring Framework
+ JDK 10
+ Docker

## Run application (docker)
$ docker-compose up 

## Questions
How would you improve the system?
+ Add a cache mechanism to the database queries.
+ Add a gateway and a service discovery like Eureka.
+ Add a circuit breaker to control cascading service failure.

How would you avoid your order API to be overflow?
+ Add a back pressure mechanism provided by WebFlux (Spring Boot 2 + Spring Framework 5).