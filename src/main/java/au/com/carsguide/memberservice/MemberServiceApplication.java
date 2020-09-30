package au.com.carsguide.memberservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
public class MemberServiceApplication {
	private static final Logger logger = LoggerFactory.getLogger(MemberServiceApplication.class);

	public static void main(String[] args) {
		logger.info("Starting service");
		SpringApplication.run(MemberServiceApplication.class, args);
	}

}
