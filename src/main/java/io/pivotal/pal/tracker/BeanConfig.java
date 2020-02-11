package io.pivotal.pal.tracker;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

	@Bean
	TimeEntryRepository timeEntryRepository() {
		return new InMemoryTimeEntryRepository();
	}

}
