# member-service
Operations for maintaining member details

## API Swagger/Open API Documentation

http://host:8080/swagger-ui.html

Screenshot of API documentation
![alt text](https://github.com/tarikecg/member-service/blob/master/docs/Swagger-ui1.png?raw=true)


## How to run

### Testing

mvn test

### MySQL DB Setup

* Create new DB named `carsguide`
* Run the following script:

member-service\resources\db\init.sql

### Maven

mvn install


### Containerized (Docker)

docker build -t carsguide/member-service .

docker-compose up
