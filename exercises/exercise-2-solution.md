# Exercise 2 - solution

* Added dependencies

```xml
<dependencies>
    <dependency>
        <groupId>com.h2database</groupId>
        <artifactId>h2</artifactId>
        <scope>runtime</scope>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
</dependencies>
```

* Introduce `Fortune entity` (example, your package and naming may vary of course)

```java
package io.pivotal.workshops.cnd.database;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
public class Fortune {

    public Fortune()    {}

    public Fortune(String text) {
        this.text = text;
    }

    @Id
    @GeneratedValue
    private Long id;

    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text)  {
        this.text = text;
    }
}
```

* Introduce a `FortuneRepository` based on `JpaRepository` to load a random `Fortune` from the database

```java
package io.pivotal.workshop.workshopfortuneservice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FortuneRepository extends JpaRepository<Fortune, Long> {

    // This query is not efficient, but ok for such s small DB
    @Query(value="select * from fortune order by rand() limit 1", nativeQuery = true)
    Fortune findRandomFortune();

}
```

* Introduce a `CommandLineRunner`` to insert random Fortune texts in the database

```java
@Bean
	CommandLineRunner loadDatabase(FortuneRepository fortuneRepo) {
		return args -> {
			LOGGER.debug("Bootstrapping database...");
			fortuneRepo.save(new Fortune("Do what works."));
			fortuneRepo.save(new Fortune("Do the right thing."));
			fortuneRepo.save(new Fortune("Always be kind."));
			fortuneRepo.save(new Fortune("You learn from your mistakes... You will learn a lot today."));
			fortuneRepo.save(new Fortune("You can always find happiness at work on Friday."));
			fortuneRepo.save(new Fortune("You will be hungry again in one hour."));
			fortuneRepo.save(new Fortune("Today will be an awesome day!"));
			LOGGER.info("Fortune Repo record count: {}", fortuneRepo.count());
			fortuneRepo.findAll().forEach(x -> LOGGER.debug(x.toString()));
		};

	}
```

* Build project again

```bash
./mvnw clean package
```

* Push the application to Cloud Foundry

```bash
cf push
```