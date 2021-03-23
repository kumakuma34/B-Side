package bside.writing.Repository;

import bside.writing.domain.challenge.Challenge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ChallengeRepository extends JpaRepository<Challenge, Long> {
    @Query("SELECT c FROM Challenge c ORDER BY c.startDt, c.currentParticipant desc")
    List<Challenge> findAllByStartDt();
}
