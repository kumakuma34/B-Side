package bside.writing.Repository;

import bside.writing.domain.challenge.ChallengeTheme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChallengeThemeRepository extends JpaRepository<ChallengeTheme, Long> {
    @Query("SELECT c.theme_name FROM ChallengeTheme c where challenge_id = :challenge_id")
    List<String> findByChallengeId(
            @Param("challenge_id") Long id
    );
}
