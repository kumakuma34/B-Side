package bside.writing;

import bside.writing.Entity.Challenge;
import bside.writing.Entity.ChallengeTheme;
import bside.writing.Repository.NewChallengeThemeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ChallengeThemeTest {
    @Autowired
    NewChallengeThemeRepository newChallengeThemeRepository;

    @Test
    public void save() throws Exception{
        //Given
        ChallengeTheme sample = ChallengeTheme.builder()
                .challenge_id(1L)
                .theme_id(1L)
                .build();

        newChallengeThemeRepository.save(sample);



    }
}
