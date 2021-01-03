package com.innomes.main.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;


@Configuration
@EnableScheduling
public class ScheduledConfig {
	private static Logger logger = LoggerFactory.getLogger(ScheduledConfig.class);

	@Bean
	public TaskScheduler scheduler() {
		logger.info("scheduler");
		ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
		scheduler.setPoolSize(4);
		return scheduler;
	}

}
