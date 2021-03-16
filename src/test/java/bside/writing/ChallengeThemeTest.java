package bside.writing;

import bside.writing.domain.challenge.ChallengeTheme;
import bside.writing.Repository.ChallengeThemeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ChallengeThemeTest {
    @Autowired
    ChallengeThemeRepository challengeThemeRepository;

    @Test
    public void save() throws Exception{
        //Given
        ChallengeTheme sample = ChallengeTheme.builder()
                .challenge_id(1L)
                .theme_id(1L)
                .build();

        challengeThemeRepository.save(sample);

    }

    @Test
    public void 조회() throws Exception{
        List<ChallengeTheme> result = challengeThemeRepository.findAll();
        for(int i = 0 ; i <result.size(); i ++){
            System.out.println(result.get(i).getChallenge_id() + " : " + result.get(i).getChallenge_theme_id());
        }
    }
}
