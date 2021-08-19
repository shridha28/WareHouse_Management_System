Docker readme

This folder contains 
1. Dockerfile - which is used to build the spring boot application. This can also be used as a standalone application but will still need mongo db on your local host machine to work

- In order to install mongodb on your local, follow the below instructions:
To locally install mongodb, follow the link - https://docs.mongodb.com/guides/server/install/
 Run the following commands <br />
  a) show dbs -> Displays databases <br />
  b) use admin -> Selects database admin <br /> 
  c) db.createUser(    <br />
  {   <br />
    user: 'admin',  <br />
    pwd: passwordPrompt(),  <br />
    roles: [ "userAdminAnyDatabase","dbAdminAnyDatabase", "readWriteAnyDatabase" ] <br />
   } <br />
  ); -> create a user  <br />
  d) use wmsdb -> creates database for dev profile <br />
  e) use wmsdbtest -> creates database for test profile <br />
  
- Clone the repository <br />
- Update the application.properties and application-test.properties with username and creds.  <br />
- Run the command -> mvn install and a jar will be created in the target folder.  <br />
- Run the jar using command -> java -jar WMS-1.0.0.jar  <br />
 
  
2. docker-compose - This is used to bring up all containers which include springboot application, mongodb container with the necessary user creds and permissions, swagger container to test the api. This is the easiest way to run the application.

alternatively, you can also run run-app.sh on any linux machine. This will create all the necessary folders which needs to be mounted for the docker containers to store logs and config file. 

PS: Swagger will only work in case the container is running on your local machine. Incase you are running a VM on top of your local machine, the api's via swagger will lead to CORS isssue. You can circumvent the problem by disabling CORS on your browser.

