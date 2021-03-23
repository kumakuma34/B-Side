package bside.writing.challengeTest;

import bside.writing.Service.ChallengeService;
import bside.writing.domain.challenge.Challenge;
import bside.writing.Repository.ChallengeRepository;
import bside.writing.dto.ChallengeDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
public class ChallengeServiceTest {
    @Autowired
    ChallengeRepository challengeRepository;

    @Test
    public void 조회() throws Exception{


        List<Challenge> list = challengeRepository.findAll(Sort.by(Sort.Direction.DESC, "startDt"));

        for(int i = 0 ; i < list.size(); i++){
            System.out.println(list.get(i).toString());
        }

    }
}