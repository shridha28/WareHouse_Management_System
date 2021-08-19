# WareHouse_Management_System
Dockerized SpringBoot Application for WareHouse Management System. It exposes APIs for storing and retrieving products and product articles (Inventory).

## Introduction
The WMS (Warehouse Management System) is an application developed in SpringBoot and java to persist products and articles required for the products using MongoDB. It is mainly a backend application and exposes apis to execute CRUD operations on Products and Inventory (Articles).

## Technologies
JDK 11
Mongo DB
Swagger UI
Sonar Qube for Code Analysis

Products:

POST - /api/products -> Import products by uploading a JSON file.
GET -  /api/products -> Get all products.
DELETE - /api/products/{productId} -> Delete a product
GET -  /api/products/{productId} -> Get a product

Inventory:

POST - /api/articles -> Import articles by uploading a JSON file.
GET -  /api/articles -> Get all articles.

## Launch
Steps to run the application

1) Clone the repository
2) In local machine, run a local instance of mongo server(27017) and create two databases
  - wmsdb for default profile
  - wmsdbtest for test profile
3) Update the application.properties and application-test.properties with username and password
4) Run mvn install . A jar will be created in the target folder.
5) Run the jar using command -> java -jar <jarfilename>.jar
