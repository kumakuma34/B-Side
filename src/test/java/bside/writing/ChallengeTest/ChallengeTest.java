package bside.writing.ChallengeTest;

import bside.writing.Repository.ChallengeMemberRepository;
import bside.writing.Service.ChallengeMemberService;
import bside.writing.Service.ChallengeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ChallengeTest {

    @Autowired
    ChallengeMemberRepository challengeMemberRepository;
    @Autowired
    ChallengeMemberService challengeMemberService;
    @Autowired
    ChallengeService challengeService;

    @Test
    public void 챌린지참여_중복검(){
        //Given
        Long challengeId = 47L;
        Long uid = 47L;

        //when
        boolean isDup = challengeMemberService.checkDuplicate(challengeId,uid);

        System.out.println(isDup);
    }

    @Test
    public void 챌린지참여자증가(){
        //Given
        Long challengeId = 47L;
        Long memberId = 47L;

        //when
        challengeMemberService.submitCntIncrease(challengeId,memberId);
        System.out.println(challengeMemberRepository.findByChallengeAndMember(challengeId,memberId).get().getSubmitArticleCnt());
    }

    @Test
    public void 달성률검사(){
        //Given
        Long challengeID = 47L;
        Long memberId = 47L;

        //when
        System.out.println(challengeService.calcAchievementRate(challengeID,memberId));
    }


}
