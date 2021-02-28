package bside.writing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import bside.writing.templateClass.DaoRetriever;
import javax.persistence.NoResultException;
import java.util.Optional;

@SpringBootApplication
public class WritingApplication {

	public static void main(String[] args) {
		SpringApplication.run(WritingApplication.class, args);
	}
}
