package bside.writing.ChallengeTest;


import bside.writing.Repository.ChallengeRepository;
import bside.writing.Service.ChallengeMemberService;
import bside.writing.domain.challenge.Challenge;
import ch.qos.logback.core.net.SyslogOutputStream;
import org.apache.tomcat.jni.Local;
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

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ChallengeSearchTest {

    @Autowired
    ChallengeRepository challengeRepository;
    @Autowired
    ChallengeMemberService challengeMemberService;
    @Test
    public void 전체조회(){
        Page<Challenge> list = challengeRepository.findOpenChallenge(PageRequest.of(0,15, Sort.by("startDt").descending().and(Sort.by("currentParticipant"))));
        List<Challenge> result = list.getContent();
        list.forEach(e->System.out.println(e.toString()));
    }

    @Test
    public void 참여중챌린지조회(){
        Page<Challenge> list = challengeRepository.findInChallenge(30L, PageRequest.of(0,10));
        List<Challenge> result = list.getContent();
        list.forEach(e->System.out.println(e.toString()));
    }

    @Test
    public void 내챌린지(){
        Page<Challenge> list = challengeRepository.findMyChallenge(1L, PageRequest.of(0,10));
        List<Challenge> result = list.getContent();
        list.forEach(e->System.out.println(e.toString()));

    }

    @Test
    public void 참여중인모든챌린지조회(){
        List<Challenge> list = challengeRepository.findAllInChallenge(30L);
        list.forEach(e->System.out.println(e.toString()));
    }
    @Test
    public void joinChallenge(){
        //given
        //Random random = new Random();
        //Long uid = Long.valueOf(random.nextInt(random.nextInt(30)));
        challengeMemberService.joinChallenge(7L,30L);

    }


}
