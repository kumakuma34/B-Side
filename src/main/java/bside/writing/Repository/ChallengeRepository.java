package bside.writing.Repository;

import bside.writing.domain.challenge.Challenge;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.awt.print.Pageable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface ChallengeRepository extends JpaRepository<Challenge, Long> {
    @Query(value = "SELECT * FROM challenge c WHERE c.current_participant < c.max_participant AND c.status = 0 order by start_dt , current_participant DESC" ,nativeQuery = true)
    Page<Challenge> findOpenChallenge(PageRequest pageable);

    @Query(value = "SELECT * FROM challenge c INNER JOIN challenge_member m ON c.challenge_id = m.challenge_id " +
            "where m.member_id = ?1 AND c.status = 1 " +
            "order by DATE_ADD(c.start_dt, INTERVAL c.duration * 7 DAY)", nativeQuery = true)
    Page<Challenge> findInChallenge(Long id, PageRequest pageable);

    @Query(value = "SELECT * FROM challenge c WHERE c.created_id = ?1 AND c.status = 0 order by c.start_dt desc", nativeQuery = true)
    Page<Challenge> findMyChallenge(Long id, PageRequest pageable);

    @Query(value = "SELECT * FROM challenge c WHERE c.created_id = ?1 AND c.status = 2 order by c.start_dt desc", nativeQuery = true)
    Page<Challenge> findMyDoneChallenge(Long id, PageRequest pageable);

    @Query(value = "SELECT * FROM challenge c INNER JOIN challenge_member m ON c.challenge_id = m.challenge_id " +
            "where m.member_id = ?1" , nativeQuery = true)
    List<Challenge> findAllInChallenge(Long id);

}
