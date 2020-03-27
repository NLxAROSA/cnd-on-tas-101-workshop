# Exercise 3 - Externalize database configuration and discover this at runtime 

## Goal
 
* Provision a dedicated MySQL database instance on CF using the self-service capabilities of CF 
* Load the random `Fortune` Cookie text from the MySQL database when running on Cloud Foundry instead of H2
* Load the database configuration from a Spring Cloud Config Server when running on Cloud Foundry instead of hard-coding or bundling environment-specific configuration along with your application

## Steps

This exercise is a bit harder so be sure to check out the [hints](#hints) in case you get stuck

* Start a Free MySQL Database Service instance (p.mysql) on Cloud Foundry. `db-small` is the name of free service plan

Syntax to start a service:

```
cf create-service <service-name> <service-plan> <service-instance-name>
```

```bash
cf create-service p.mysql db-small workshop-database
```

* Introduce:
  * Dependency management for `spring-cloud-services-dependencies` and `spring-cloud-dependencies` (see [hints](#hints) section)
  * Maven dependencies: `spring-boot-starter-security` and `spring-cloud-services-starter-config-client` 
* Introduce an `application.yml` in directory called `configuration` in the root of this project. This a bit of a shortcut (remember we just said that you shouldn't bundle configuration with your application!) but we use this file to be served by the config server via Git. Think about how you would like to configure this in your own production environment. Another Git repo? CredHub? Vault? Or maybe some other type of secure store?
* Provision a `Config Server` in Cloud Foundry and expose the configuration from this project

```
cf create-service p.config-server standard workshop-config-server -c '{"git": { "uri": "https://github.com/NLxAROSA/cnd-on-pivotal-platform-101-workshop.git", "searchPaths": "configuration" } }'
```

* Disable security for now by adding a `SecurityConfig` and expose the actuator management endpoints (don't do this in production! Have proper endpoint authentication and authorization there unless your API is completely public)
* Configure JPA `spring.jpa` to use the `org.hibernate.dialect.MySQL55Dialect` in the newly created `application.yml` file in the `configuration` folder and make sure the table to store the fortune texts in is created at startup of the application 
* Bind the services to your application by adding services instances `workshop-db` and `workshop-config-server' to the `manifest.yml`. 
* Run your application locally (Note for local development we still run H2 and don't use the config server)
* If the application is working fine push the updated version of your application to Cloud Foundry

```bash
cf push 
``` 

* Test the application in the browser

Nice work you now deployed your Fortune Service to PCF with a persistent database and load the configuration from the config server!

## Hints

* Keep H2 for local development
* We don't create a Spring Cloud Config Server Spring Boot project ourselves. We provision a service instance from the Cloud Foundry Marketplace. This works exactly the same as provisioning the database server (or any other Marketplace service).
* For local development we don't run a Spring Cloud Config Server, we'll just use the default/fallback configuration. Why would you need a default/fallback configuration?
* Add the following dependency management section in your `pom.xml`

```xml
<dependencyManagement>
		<dependencies>
			<dependency>
				<groupId>io.pivotal.spring.cloud</groupId>
				<artifactId>spring-cloud-services-dependencies</artifactId>
				<version>2.1.4.RELEASE</version>
				<type>pom</type>
				<scope>import</scope>
			</dependency>
			<dependency>
				<groupId>org.springframework.cloud</groupId>
				<artifactId>spring-cloud-dependencies</artifactId>
				<version>Greenwich.SR3</version>
				<type>pom</type>
				<scope>import</scope>
			</dependency>
		</dependencies>
	</dependencyManagement>
```
* Check the [solution](exercise-3-solution.md) for valuable background information and to see whether you missed anything.