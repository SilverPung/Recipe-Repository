
# Recipe-Repository

# About the project:


# How to run the project:

To run the project you need to have docker and docker-compose installed on your machine.
Then you can run the following commands:
```
mvn clean install 
```
or to skip the tests:
```
mvn clean install -DskipTests
```
and then:
```
docker-compose up --build
```
The project will be available at http://localhost:8080