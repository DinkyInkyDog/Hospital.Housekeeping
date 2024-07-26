package hospital.housekeeping;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("hospital.housekeeping")
public class HousekeepingApplication {

	public static void main(String[] args) {
		SpringApplication.run(HousekeepingApplication.class, args);

	}

}
