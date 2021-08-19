# WareHouse_Management_System
Dockerized SpringBoot Application for WareHouse Management System. It exposes APIs for storing and retrieving products and product articles (Inventory)

This SpringBoot Application is a dockerized java application that exposes the following endpoints to import products and articles in MongoDB.

Products

POST - /api/products -> Import products by uploading a JSON file.
GET -  /api/products -> Get all products.
DELETE - /api/products/{productId} -> Delete a product


Inventory
POST - /api/articles -> Import articles by uploading a JSON file.
GET -  /api/articles -> Get all articles.

Steps to run the application

1) Clone the repository
2) In local machine, run a local instance of mongo server(27017) and create two databases
  - wmsdb for default profile
  - wmsdbtest for test profile
3) Update the application.properties and application-test.properties with username and password
4) Run mvn install . A jar will be created in the target folder.
5) Run the jar using command -> java -jar <jarfilename>.jar