package bside.writing.challengeTest;

import bside.writing.Service.ChallengeThemeService;
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
    @Autowired
    ChallengeThemeService challengeThemeService;
    @Test
    public void save() throws Exception{
        //Given
        String testInput = "#문창주 #양아치 #문창주는양아치";
        Long testId = 1L;
        ChallengeTheme sample = ChallengeTheme.builder()
                .challenge_id(1L)
                .theme_name("#문창주 #양아치 #문창주는양아치")
                .build();

        challengeThemeService.insertChallengeTheme(testInput, testId);
    }

    @Test
    public void 조회() throws Exception{
        List<ChallengeTheme> result = challengeThemeRepository.findAll();
        for(int i = 0 ; i <result.size(); i ++){
            System.out.println(result.get(i).getChallenge_id() + " : " + result.get(i).getChallenge_theme_id());
        }
    }
}
