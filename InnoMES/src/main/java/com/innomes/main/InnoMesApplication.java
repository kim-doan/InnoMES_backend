package com.innomes.main;

import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.innomes.main.system.service.DateService;

@SpringBootApplication
public class InnoMesApplication {
	private static Logger logger = LoggerFactory.getLogger(InnoMesApplication.class);
	@Autowired
	private DateService dateService;

	@Value("${spring.jackson.time-zone}")
	private String zoneId;

	@Value("${spring.profiles.active}")
	private String activeName;
	
//	static {
//		System.setProperty("spring.config.location", "./FactoryView/config/application.yml, ./FactoryView/config/application.properties");
//	}
	
	@PostConstruct
	public void started() {

		TimeZone.setDefault(TimeZone.getTimeZone(zoneId));
		logger.info("\r==============================\r\r\r    active_name : " + activeName + "\r\r\r==============================\r");
		logger.info("\r==============================\r\r\r    zone_id : " + zoneId + "\r\r\r==============================\r");
		logger.info("\r==============================\r\r\r    현재시각 : " + dateService.NowTime() + "\r\r\r==============================\r");
	}
	
	public static void main(String[] args) {
		SpringApplication.run(InnoMesApplication.class, args);
	}

}
