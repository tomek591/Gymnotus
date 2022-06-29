# Gymnotus
Application to track workouts with options to save, search history and measure progress. Main purpose of this app is to learn technologies as Java, Spring, Hibernate
and test frameworks. Application still in build.

## Technology used in app:
- Java
- Spring Boot
- Maven
- Hibernate
- MySql
- Lombok
- Open API(Swagger)
- Junit 5
## Gettning started
If you want run this app you need:
- [Spring boot](https://docs.spring.io/spring-boot/docs/current/reference/html/getting-started.html)
- [JDK 17](https://www.oracle.com/java/technologies/downloads/#java17)
- [Maven 3](https://maven.apache.org/)

To start application you need to run main method from `com.example.gymnotus.GymnotusApplication` class from your IDE.

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
mvn spring-boot:run
```
You also have to configure database in `application.properties`.
It's important to set `spring.jpa.hibernate.ddl-auto=none` to create, and after first run to none.
In datasource you have to set database name, username and password.
```
spring.jpa.hibernate.ddl-auto=none
spring.jpa.show-sql=true

spring.datasource.url=jdbc:mysql://${MYSQL_HOST:localhost}:3306/yourdbname?useUnicode=true&serverTimezone=UTC
spring.datasource.username=yourusername
spring.datasource.password=yourpassword
```
If you run application you can try it from http://localhost:8080/swagger-ui/index.html#/

## Sample endpoints

![obraz](https://user-images.githubusercontent.com/101336553/175294375-ad3ba788-4e03-4728-88fa-8fe2cc17c936.png)
