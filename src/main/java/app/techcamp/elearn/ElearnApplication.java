package app.techcamp.elearn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ElearnApplication {

	public static void main(String[] args) {
		SpringApplication.run(ElearnApplication.class, args);
	}

}
