# operaBankBack

##This is operaBank backend!

Main approach was to create miniature banking system for several users
who can have several bank accounts, make transfers between all accounts,
deposit and withdraw money, check full transaction history etc. 
All passwords are protected with salts and hashes. 


### How to use:
* Install required software
* Clone the project to local computer
* Open project folder with IDEA 
* Run main method in OperaBankBackApplication class located in 'src/main/java/com/example/podermirko/operabankback/OperaBankBackApplication.java'

### Required software:
* Java JDK 14 or newer
* IntelliJ IDEA 2019 or newer

### Project dependencies and libraries:
* Check OperaBankBack.iml file

### Folder structure:
* controllers - web layer, controllers for Account and User
* models - models and mappers
* service - business logic 
* repository - layer for communicating with database
* security - security configurations

### Things yet to improve:
* implement automated testing
* AccountRepository has to be divided into few separate classes if application will be further developed.
* possibility to build database with hibernate/jpa for better overall system quality


### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.4.2/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.4.2/maven-plugin/reference/html/#build-image)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/2.4.2/reference/htmlsingle/#boot-features-jpa-and-spring-data)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.4.2/reference/htmlsingle/#boot-features-developing-web-applications)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/2.4.2/reference/htmlsingle/#using-boot-devtools)

### Guides
The following guides illustrate how to use some features concretely:

* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)

