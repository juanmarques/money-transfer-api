## Money-Transfer-API

This repository contains an API for Money Transfer

#### Run Local
````
1.Import the project to eclipse 
2.Enable Annotation Processors
   Go to project properties > Maven > Annotation Processing > Check Automatically configure JDT APT
3.Run as Java Application (Application.java)
````

#### Build the package
````
cd .\money-transfer-api\
mvn clean install
````
#### Build the image
```
docker build -t money-transfer-api .
```
#### Run the container
```
docker run -d -p 8080:8080 money-transfer-api
```

#### Stop the container
```
docker rm <container-id> -f
```
#### Technologies

* [Micronaut 1.0.3] - Micronaut is a modern, JVM-based, full stack microservices framework designed for building modular, easily testable microservice applications.
* h2-Database
* Hibernate-JPA
* Junit
* Java 8
* Maven
* Eclipse