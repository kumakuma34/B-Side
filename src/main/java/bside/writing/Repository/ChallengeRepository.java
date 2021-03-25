package bside.writing.Repository;

import bside.writing.domain.challenge.Challenge;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.awt.print.Pageable;
import java.util.List;

public interface ChallengeRepository extends JpaRepository<Challenge, Long> {
    @Query("SELECT c FROM Challenge c WHERE c.currentParticipant < c.maxParticipant")
    Page<Challenge> findOpenChallenge(PageRequest pageable);


}
