
# Recipe-Repository

# About the project:

This project is a recipe repository where you can create, read, update and delete recipes.\
The project is built using Spring Boot, Spring Data JPA, PostgreSQL, Docker and Docker-compose.



# How to run the project:

## Requirements:

Java 21\
Maven 3.8.7\
Docker 27.3.1\
Docker-compose 2.29.7\

## Configuration:

The project is configured to run on port 8080.\
The database is configured to run on port 5432.\
All the configurations can be found in the application.properties file.\
The environment variables for db and app are set in .env file.

## Steps:

* Build the project:
```
mvn clean package
```
 or to skip tests
```
mvn clean package -DskipTests
```    

* Run the project:
```
docker-compose up --build
```

* Then you can access the project at:
```http://localhost:8080/```





