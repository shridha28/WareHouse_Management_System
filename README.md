# WareHouse_Management_System [![CI Workflow for WMS](https://github.com/shridha28/WareHouse_Management_System/actions/workflows/CI_Workflow.yml/badge.svg?branch=master)](https://github.com/shridha28/WareHouse_Management_System/actions/workflows/CI_Workflow.yml)
This repository contains dockerized springBoot application for WareHouse Management System. It contains APIs to store, retrive products and its relevant articles in the Inventory.

## Introduction
WMS (Warehouse Management System) is an application developed in SpringBoot and java to maintain inventory of articles and products using MongoDB. It is a backend application and provides apis to execute CRUD operations on Products and Inventories (Articles).

## Technologies
-Spring Boot <br />
-JDK 11 <br />
-Mongo DB <br />
-Swagger UI <br />
-Sonar Qube for Code Analysis <br />
-Docker <br />

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
