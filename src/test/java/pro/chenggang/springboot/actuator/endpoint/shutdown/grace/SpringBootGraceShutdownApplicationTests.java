package pro.chenggang.springboot.actuator.endpoint.shutdown.grace;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pro.chenggang.springboot.actuator.endpoint.shutdown.grace.core.EnableGraceShutdownEndpoint;

@EnableGraceShutdownEndpoint
@SpringBootApplication
public class SpringBootGraceShutdownApplicationTests {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootGraceShutdownApplicationTests.class,args);
	}

}