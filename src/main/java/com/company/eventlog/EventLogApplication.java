package com.company.eventlog;

import com.company.eventlog.service.EventLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class EventLogApplication implements CommandLineRunner {

	@Autowired
	EventLogService service;

	private static Logger logger = LoggerFactory.getLogger(EventLogApplication.class);
	public static void main(String[] args) {

		logger.info("Application started");
		SpringApplication.run(EventLogApplication.class, args);
		logger.info("Application completed");
	}

	@Override
	public void run(String... args) throws Exception {
		if (args.length == 0) {
			logger.info("Usage:\n\n\n            mvnw spring-boot:run -Dspring-boot.run.arguments=/path/to/logfile\n\n\n");
			System.exit(0);
		}
		logger.info("args[0]: " + args[0]);
		logger.info("Processing the input file...");
		service.process(args[0]);
		service.list().forEach( a -> logger.info(a.toString()));
	}
}
