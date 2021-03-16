package bside.writing.Repository;

import bside.writing.Entity.Challenge;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewChallengeRepository extends JpaRepository<Challenge, Long> {

}
