# Student Manager

This is an automated student management application. Provides student record creation, update and delete facilities

### Prerequisites

Java 8, Spring Tool Suite or Eclipse, Apache Maven, PostgreSQL 9, PgAdmin


### Installing

```
Clone the given repository to your workspace
Three modules are availabe
* student-domain
* student-core
* studen-manager-web

Open Eclipse or STS and right click on each project and select configure
Select convert to maven project
You can see the converted maven projects in the Project explorer section
Install the PostgreSQL if you don't have install it already 
Create a database as student-manager
Please update the database credentials in the SysConfig file before build the student-core project
```
### Build Order
```
1. clean and install the student-domain module
2. clean and install the student-core module
3. clean and install the student-manager-web module
```
studen-manager-web module developed as a Spring Boot application with AngularJS front end. 


## Running the tests

```
There are few unit test cases have provided to test the system configurations and the apis
You can access them in src/test/java package in the student-manager-web project
To test
* 1. First clean and build the student-domain project
* 2. Then clean and build the student-core project
* 3. Finally clean and build the student-manager-web project. 
```

Given test cases are provided to test the entire flow of the application apis. 

```
StudentControllerTest
```

## Deployment and Run

```
* 1. Select the student-manager-web project and select Run As -> Java Application
* 2. In the dialog box select the SpringBootStudentApp class
* 3. Click Run
* 4. Once the project is started you should be able to see the following line in the console
     *Started SpringBootStudentApp in 5.49 seconds (JVM running for 5.888)*
* 5. Open a web browser and type http://localhost:8080/student-manager-web 
* 6. You should be able to see the Student Management application.
* 7. I have subitted sample screen shots for your reference

```

## Special Note
Entire system architecture has developed in a way which provide loose coupling, scalability,  easy maintenance and several other features. I have added the AngularJS Front End code and added Spring Boot support for the student-manager-web module to demonstrate the system functionality with the actual UI. 
