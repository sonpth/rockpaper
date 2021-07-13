package code.resmed.rockpaper;

import java.text.NumberFormat;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.core.env.Environment;

/**
 * Main Spring Boot application for harmony-hosted-service.
 */
@SpringBootApplication
@ServletComponentScan
public class RockPaperApp {
		
	private static final Logger LOGGER = LoggerFactory.getLogger(RockPaperApp.class);
	
	public static void main(String[] args) {
		SpringApplication.run(RockPaperApp.class, args);
		getRuntimeMemoryInfo();
	}
	
	private static void getRuntimeMemoryInfo() {
		Runtime runtime = Runtime.getRuntime();
		final NumberFormat format = NumberFormat.getInstance();
		final long maxMemory = runtime.maxMemory();
		final long allocatedMemory = runtime.totalMemory();
		final long freeMemory = runtime.freeMemory();
		final long mb = 1024 * 1024;
		final String mega = " MB";
		LOGGER.info("========================== Memory Info ==========================");
		LOGGER.info("Free memory: " + format.format(freeMemory / mb) + mega);
		LOGGER.info("Allocated memory: " + format.format(allocatedMemory / mb) + mega);
		LOGGER.info("Max memory: " + format.format(maxMemory / mb) + mega);
		LOGGER.info("Total free memory: " + format.format((freeMemory + (maxMemory - allocatedMemory)) / mb) + mega);
		LOGGER.info("=================================================================\n");	
	}
	
	@Autowired
	private Environment env;
	
	@PostConstruct
	public void print() {
		String[] properties = {
				"spring.datasource.url", 
				"spring.datasource.username",
				"spring.datasource.hikari.minimum-idle",
				"spring.datasource.hikari.idle-timeout",
				"server.port",
				"server.servlet.context-path"
		};
		
		LOGGER.info("========================== Property Info ==========================");
		for(String prop : properties) {
			LOGGER.info(prop + "=" + env.getProperty(prop));
		}
		LOGGER.info("=================================================================\n");	
	}	
}
