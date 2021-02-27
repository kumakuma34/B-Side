package bside.writing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import bside.writing.Repository.DaoRetriever;
import javax.persistence.NoResultException;
import java.util.Optional;

@SpringBootApplication
public class WritingApplication {

	public static void main(String[] args) {
		SpringApplication.run(WritingApplication.class, args);
	}

	public static <T> Optional<T> findOrEmpty(final DaoRetriever<T> retriever) {
		try {
			return Optional.of(retriever.retrieve());
		} catch (NoResultException ex) {
			//log
		}
		return Optional.empty();
	}
}
