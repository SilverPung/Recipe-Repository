
# Recipe-Repository

# About the project:

This project is a recipe repository where you can create, read, update and delete recipes.\
The project is built using Spring Boot, Spring Data JPA, PostgreSQL, Docker and Docker-compose.



# How to run the project:

## Requirements:

Java 21 or newer\
Maven 3.8.x or newer\
Docker 27.3.x or newer\
Docker-compose 2.29.x or newer

## Configuration:

The project is configured to run on port 8080.\
The database is configured to run on port 5432.\
All the configurations can be found in the application.properties file.\
The environment variables for db and app are set in .env file.

| Environment Variable | Description                        | Example                     |
|----------------------|------------------------------------|-----------------------------|
| DB_NAME              | The name of the database           | recipe_repository           |
| DB_USERNAME          | The username for the database      | recipe_repository_user      |
| DB_PASSWORD          | The password for the database      | password                    |
| TEST_USERNAME        | The username for the test database | recipe_repository_test_user |
| TEST_PASSWORD        | The password for the test database | password                    |

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

## How to connect to db using database client:

You can connect to the database using any database client like DBeaver, DataGrip, etc.
using the following configurations:

* Database Url: jdbc:postgresql://localhost:5432/ *Your database name from .env file*
* Username: *Your username from .env file*
* Password: *Your password from .env file*

then you can connect to the database.




