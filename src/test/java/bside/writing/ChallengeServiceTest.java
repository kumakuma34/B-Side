package bside.writing;

import bside.writing.Entity.Challenge;
import bside.writing.Repository.ChallengeRepository;
import bside.writing.Repository.MemberRepository;
import bside.writing.Repository.NewChallengeRepository;
import bside.writing.Service.ChallengeService;
import bside.writing.Service.MemberService;
import bside.writing.dto.ChallengeDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class ChallengeServiceTest {
    @Autowired
    NewChallengeRepository newChallengeRepository;

    @Test
    public void join() throws Exception{
        //Given
        LocalDate cur = LocalDate.now();
        Challenge sample = Challenge
                .builder()
                .challenge_detail("챌린지3")
                .challenge_title("서평")
                .cover_img(1)
                .duration(4)
                .max_participant(10)
                .start_dt(cur)
                .created_id(1)
                .modified_id(2)
                .build();

        //when
        newChallengeRepository.save(sample);

    }

    @Test
    public void 조회() throws Exception{
        //Given
        List<Challenge> challenges = newChallengeRepository.findAll();

        for(int i = 0 ; i < challenges.size() ; i++){
            ChallengeDto challengeDto = new ChallengeDto(challenges.get(i));
            System.out.println(challengeDto.getChallenge_detail() + " : " + challenges.get(i).getChallenge_detail());
            Assertions.assertThat(challenges.get(i).getChallenge_detail()).isEqualTo(challengeDto.getChallenge_detail());
        }



    }

    @Test
    public void updateOrSave() throws Exception{
        //Given
//        Optional<Challenge> result = newChallengeRepository.findById(1L);
//        if(result.isPresent()){
//            result.get().setChallenge_detail("내용을바꿈2222");
//            newChallengeRepository.save(result.get());
//        }



    }
}
