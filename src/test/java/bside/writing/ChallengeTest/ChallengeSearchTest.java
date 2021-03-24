package bside.writing.ChallengeTest;


import bside.writing.Repository.ChallengeRepository;
import bside.writing.domain.challenge.Challenge;
import ch.qos.logback.core.net.SyslogOutputStream;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.data.domain.Pageable;
import java.util.List;



@RunWith(SpringRunner.class)
@SpringBootTest
public class ChallengeSearchTest {

    @Autowired
    ChallengeRepository challengeRepository;

    @Test
    public void 전체조회(){
        Page<Challenge> list = challengeRepository.findAbleToJoinChallenge(PageRequest.of(0,15, Sort.by("startDt").descending().and(Sort.by("currentParticipant"))));
        List<Challenge> result = list.getContent();
        list.forEach(e->System.out.println(e.toString()));


    }


}
