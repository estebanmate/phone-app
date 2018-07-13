FROM openjdk:10.0.1-jdk
ADD ./target/order-service-*.jar app.jar
ENTRYPOINT ["java","-Dspring.data.mongodb.uri=mongodb://mongodb-order-service:27018/orders","-Dcatalog.service.url=http://catalog-service:8087","-jar","/app.jar"]