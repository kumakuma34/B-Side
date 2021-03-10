package bside.writing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import bside.writing.templateClass.DaoRetriever;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.persistence.NoResultException;
import java.util.Optional;

@EnableJpaAuditing //JPA Auditing 활성화
@SpringBootApplication
public class WritingApplication {

	public static void main(String[] args) {
		SpringApplication.run(WritingApplication.class, args);
	}
}
