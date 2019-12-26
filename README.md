# Project X

This is an automated project x application. Provides  record creation, update and delete facilities

### Prerequisites

Java 8, Spring Tool Suite or Eclipse, Apache Maven, PostgreSQL 9, PgAdmin


### Installing

```
Clone the given repository to your workspace
Three modules are availabe
* project-x-domain
* project-x-core
* project-x-web

Open Eclipse or STS and right click on each project and select configure
Select convert to maven project
You can see the converted maven projects in the Project explorer section
Install the PostgreSQL if you don't have install it already 
Create a database as project-x
Please update the database credentials in the SysConfig file before build the project-x-core project
```
### Build Order
```
1. clean and install the project-x-domain module
2. clean and install the project-x-core module
3. clean and install the project-x-web module
```
project-x-web module developed as a Spring Boot application with Swagger UI. 


## Running the tests

```
There are few unit test cases have provided to test the system configurations and the apis
You can access them in src/test/java package in the student-manager-web project
To test
* 1. First clean and build the project-x-domain project
* 2. Then clean and build the project-x-core project
* 3. Finally clean and build the project-x-web project. 
```

Given test cases are provided to test the entire flow of the application apis. 

```
StudentControllerTest
```

## Deployment and Run

```
* 1. Select the project-x-web project and select Run As -> Java Application
* 2. In the dialog box select the SpringBootStudentApp class
* 3. Click Run
* 4. Once the project is started you should be able to see the following line in the console
     *Started SpringBootStudentApp in 5.49 seconds (JVM running for 5.888)*
* 5. Open a web browser and type http://localhost:8070/project-x/swagger-ui.html 
* 6. Project-x swagger ui will be loaded in the browser.


```

## Special Note
Entire system architecture has developed in a way which provide loose coupling, scalability,  easy maintenance and several other features. 
