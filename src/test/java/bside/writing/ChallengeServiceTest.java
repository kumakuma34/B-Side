package bside.writing;

import bside.writing.Entity.Challenge;
import bside.writing.Repository.ChallengeRepository;
import bside.writing.Repository.MemberRepository;
import bside.writing.Repository.NewChallengeRepository;
import bside.writing.Service.ChallengeService;
import bside.writing.Service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
public class ChallengeServiceTest {
    @Autowired
    NewChallengeRepository newChallengeRepository;

    @Test
    public void join() throws Exception{
        //Given
        LocalDateTime cur = LocalDateTime.now();
        Challenge sample = Challenge
                .builder()
                .writing_theme("서평")
                .challenge_detail("챌린지3")
                .challenge_theme("서평")
                .cover_img("tmp2")
                .duration(4)
                .max_participant(10)
                .start_dt(cur)
                .created_id(1)
                .modified_id(2)
                .build();

        //when
        newChallengeRepository.save(sample);

    }

}
