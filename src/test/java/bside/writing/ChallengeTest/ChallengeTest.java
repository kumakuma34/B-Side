package bside.writing.ChallengeTest;

import bside.writing.Repository.BadgeRepository;
import bside.writing.Repository.ChallengeMemberRepository;
import bside.writing.Service.ChallengeMemberService;
import bside.writing.domain.badge.Badge;
import bside.writing.dto.BadgeDto;
import bside.writing.enums.BadgeCode;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class ChallengeTest {

    @Autowired
    ChallengeMemberRepository challengeMemberRepository;
    @Autowired
    ChallengeMemberService challengeMemberService;

    @Test
    public void 챌린지참여_중복검(){
        //Given
        Long challengeId = 7L;
        Long uid = 47L;

        //when
        boolean isDup = challengeMemberService.checkDuplicate(challengeId,uid);

        System.out.println(isDup);
    }



}
