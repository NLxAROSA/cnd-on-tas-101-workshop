# Exercise 2 - add a simple database to your application using H2 and Spring Data

## Goal
 
* Replace the static Fortune text by a random Fortune text that will be loaded from an H2 database.


## Steps

* Introduce H2 and Spring Data JPA Boot Starter dependencies into your pom.xml
* Introduce a JPA Entity `Fortune` with a `text` and `id` 
* Introduce a `JpaRepository` to return a random `Fortune` from the database

Hint to load a Random Fortune from the database:

```java
// This query is not efficient, but ok for a small DB
@Query(value="select * from fortune order by rand() limit 1", nativeQuery = true)
Fortune findRandomFortune();
```

* At startup of the `SpringBootApplication` insert Fortune Cookie messages in the database using a `CommandLineRunner`
* Expose the random `Fortune` Cookie text using the REST API
* Build the project using Maven and run it locally first
* Call the REST endpoint from the browser or curl/http to load the random Fortune text
* If the application is working fine then push the updated version of your application to Cloud Foundry
* Call the REST endpoint of the deployed application from the browser or curl/hhtp to load the random Fortune text

## Tips

* A `CommandLineRunner` is a marker interface that allows code to be run when it is loaded into the `ApplicationContext`.
* Spring Boot auto-configuration will detect H2 on the classpath and create a `DataSource` for you and wire it into your repository. No code or configuration required.
* Spring Data works by convention-over-configuration, which means that most of the time you'll only define interfaces and based on the naming of the methods Spring Data will generate an implementation on-the-fly for you. No code or configuration required.
* Check the [solution](exercise-2-solution.md) for valuable information and whether you missed anything