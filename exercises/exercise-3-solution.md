# Exercise 3 - solution 

* Add dependency management for Spring Cloud Services

Add to your `pom.xml`:

```xml
<dependencyManagement>
		<dependencies>
			<dependency>
				<groupId>io.pivotal.spring.cloud</groupId>
				<artifactId>spring-cloud-services-dependencies</artifactId>
				<version>2.2.3.RELEASE</version>
				<type>pom</type>
				<scope>import</scope>
			</dependency>
			<dependency>
				<groupId>org.springframework.cloud</groupId>
				<artifactId>spring-cloud-dependencies</artifactId>
				<version>Hoxton.SR3</version>
				<type>pom</type>
				<scope>import</scope>
			</dependency>
		</dependencies>
	</dependencyManagement>
```

* Add dependencies for the config client and Spring Security

Add to your `pom.xml`:

```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-security</artifactId>
    </dependency>
    <dependency>
        <groupId>io.pivotal.spring.cloud</groupId>
        <artifactId>spring-cloud-services-starter-config-client</artifactId>
    </dependency>
</dependencies>
```

* Disable security (don't do this in production!) Did you see what happens if you don't? Because Spring Security is on the class-path, Spring Boot autoconfiguration will automatically set it up for you, meaning your endpoints are now secured with user and password. Since we don't want this we'll disable it for now, but in your code that goes to production you'll probably put some nice integration with an authentication/authorization system of choice (or more likely: the choice of your organisation) in there.

```java
package io.pivotal.workshop.workshopfortuneservice;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .anyRequest().permitAll();
    }
}
```

`configuration/application.yml`

```yml
management:
  endpoints:
    web:
      exposure:
        include: "*"
```

* Configure the JPA to use the MySQL55Dialect and generate the table at startup

```yml
spring:
  jpa:
    generate-ddl: true
    properties:
      hibernate:
        dialect: org.hibernate.dialect.MySQL55Dialect
```

* Provision a config server in Cloud Foundry

```bash
cf create-service p.config-server standard workshop-config-server -c '{"git": { "uri": "https://github.com/NLxAROSA/cnd-on-tas-101-workshop.git", "searchPaths": "configuration" } }'
```

* Provision a database in Cloud Foundry

```bash
cf create-service p.mysql db-small workshop-db
```

* Bind the database and config server services to the application by introducing service instance names in manifest.yml:

```yml
  services:
    - workshop-db
    - workshop-config-server
```

Note that you can do the same via CLI by using the 'cf bind-service' command. So what's the purpose of `create-service` and `bind-service`? `create-service` provisions a `service instance` in your space according to the specified `plan`. This usually (but not always) involve the allocation of resources like VMs. `bind-service` generates a set of credentials for your application to use the service and exposes them in the runtime environment of CF. Spring Boot picks this up automatically. Externalizing your configuration in this way ensures your applications are portable and it also opens up the way to more advanced scenarios like automatic credential rotation. It also frees the developers of having to store and maintain credentials on their end.

* Build project again

```bash
./mvnw clean package
```

* Push the new version of your application to Cloud Foundry

```bash
cf push 
``` 