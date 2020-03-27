package io.pivotal.workshops.cnd.serviceregistry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class ServiceRegistryDemoApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(ServiceRegistryDemoApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ServiceRegistryDemoApplication.class, args);
	}

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
			fortuneRepo.save(new Fortune("Wash your hands, with soap, often."));
			fortuneRepo.save(new Fortune("Keep your distance."));
			fortuneRepo.save(new Fortune("Don't congregate."));
			fortuneRepo.save(new Fortune("Work remote if you can."));
			fortuneRepo.save(new Fortune("The bad times will pass!"));
			fortuneRepo.save(new Fortune("2020 will be the year of the Linux desktop."));
			LOGGER.info("Fortune Repo record count: {}", fortuneRepo.count());
			fortuneRepo.findAll().forEach(x -> LOGGER.debug(x.toString()));
		};

	}

}
