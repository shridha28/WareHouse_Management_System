# WareHouse_Management_System
Dockerized SpringBoot Application for WareHouse Management System. It exposes APIs for storing and retrieving products and product articles (Inventory).

## Introduction
The WMS (Warehouse Management System) is an application developed in SpringBoot and java to persist products and articles required for the products using MongoDB. It is mainly a backend application and exposes apis to execute CRUD operations on Products and Inventory (Articles).

## Technologies
-Spring Boot <br />
-JDK 11 <br />
-Mongo DB <br />
-Swagger UI <br />
-Sonar Qube for Code Analysis <br />

## APIs

**Application and MongoDB health check:** <br />
/actuator/health 

**Products:**

POST - /api/products -> Import products by uploading a JSON file. <br />
GET -  /api/products -> Get all products. <br />
DELETE - /api/products/{productId} -> Delete a product <br />
GET -  /api/products/{productId} -> Get a product <br />

**Inventory:**

POST - /api/articles -> Import articles by uploading a JSON file. <br />
GET -  /api/articles -> Get all articles. <br />

## Launch
Steps to run the application

Please refer to the link [steps](https://github.com/shridha28/WareHouse_Management_System/blob/master/WMS_Docker/README.md) to run the application locally/docker container.
