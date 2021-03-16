package bside.writing.Repository;

import bside.writing.Entity.Challenge;
import bside.writing.Entity.ChallengeTheme;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewChallengeThemeRepository extends JpaRepository<ChallengeTheme, Long> {

}
