package bside.writing;

import bside.writing.Entity.Challenge;
import bside.writing.Entity.ChallengeTheme;
import bside.writing.Repository.NewChallengeThemeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

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

    @Test
    public void 조회() throws Exception{
        List<ChallengeTheme> result = newChallengeThemeRepository.findAll();
        for(int i = 0 ; i <result.size(); i ++){
            System.out.println(result.get(i).getChallenge_id() + " : " + result.get(i).getChallenge_theme_id());
        }
    }
}
