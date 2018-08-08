# StoreManagementBoot
REST API for snack store management using Spring Boot and H2 Database. REST API for small snack store using Spring MVC.
The API receives request under specific URLs, retrieving data from the database and performing actions associated with each
URL.
Database is generated using an h2 memory database, so you don't need any script in order to be able to load the schema nor the data.

## Getting Started

To get project for testing and developing purposes just perform:

```
git clone https://github.com/LuisMario01/StoreManagementBoot.git
```

or download as zip file. To get a WAR file, contact author.

After dowloading the project in the desired location, Spring STS is recommended to open it (if you want to keep developing it).
To import it from Spring STS, go to File > Open Projects From File System and browse the location where git/zip was put under.

### Prerequisites
Tools required to develop or test the API:

```
Java 8
Pivotal tC Server Developer Edition v4.0./ [Apache Tomcat](http://www.baeldung.com/tomcat-deploy-war)
Postman
Snack store - REST API.PostmanCollection.json attached to test requests.
Maven 
GNU/Linux is recommended to run project.
```

## Installing and usage

### With an IDE
Copy WAR file, go to the webapps folder of your server and put it  there.
If you are deploying it from Spring STS select the Run>Run, select Pivotal Server and the project will be automatically deployed.
Import [JSON file](StoreManagementBoot.PostmanCollection.json) to your Postman, by selecting option Import>From file (browsing the location where the project is located).

Once project is deployed and database is running, test it with Postman and the collections provided before.

### As a Springboot/Maven standalone project
For more information, please refer to [SpringBoot official documentation](https://spring.io/guides/gs/spring-boot/).
Reach the location in your system where you have downloaded the repository content. Go to storemanagement (it is the root of the project), make sure you are in the same level as the pom.xml file, and then type:

```
mvn spring-boot:run
```
This command will start the application and you should see the initializing process coming in your screen. Remember, to use this, mvn must be available in your PATH. The last thing you should be seeing are the test queries for the basic structure of the database, performed by hibernate.

## Built With

* [Spring STS](https://spring.io/tools) - IDE used.
* [SpringBoot](https://spring.io/projects/spring-boot) - Web framework used.
* [Maven](https://maven.apache.org/) - Dependency Management.
* [H2 database](http://www.h2database.com/html/main.html) - Database engine.
* [Postman](https://www.getpostman.com/) - Used to test the API
* [EclEmma](https://www.eclemma.org/) - Code coverage tool as an extension to Spring STS.

## Authors

* **Luis De Paz** - *Initial work* - [StoreManagement](https://github.com/LuisMario01/StoreManagement)

## License

This project is licensed under the GPL v3.0. License - see the [LICENSE.md](LICENSE.md) file for details




