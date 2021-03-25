package bside.writing.badge.badgeRepo;


import bside.writing.Repository.BadgeRepository;
import bside.writing.domain.badge.Badge;
import bside.writing.domain.badge.BadgeCode;
import bside.writing.dto.BadgeDto;
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
public class BadgeRepoTest {

    @Autowired
    BadgeRepository badgeRepository;

    @Test
    public void 뱃지_생성(){
        BadgeDto badgeDto = BadgeDto.builder()
                .memberId(30L)
                .badgeCode(BadgeCode.CHALLENGE_FINISH.name())
                .badgeValue("1")
                .build();
        Badge entity = badgeDto.toEntity();
        badgeRepository.save(entity);
        List<Badge> badges = badgeRepository.findByMemberId(30L).get();
        System.out.println("badges = " + badges.toString());
    }

    @Test
    public void 뱃지_코드로_조회(){
        뱃지_생성();
        String badgeCode = BadgeCode.ARTICLE_COMMIT.name();
        List<Badge> badges = badgeRepository.findByMemberIdAndBadgeCode(30L, badgeCode).get();
        System.out.println("badges.toString() = " + badges.toString());
    }

    @Test
    public void 뱃지_맴버아이디_뱃지코드로_조회(){
        String badgeCode = BadgeCode.ARTICLE_COMMIT.name();
        List<Badge> badges = badgeRepository.findByMemberIdAndBadgeCode(30L, badgeCode).get();
        System.out.println("badges.toString() = " + badges.toString());
    }

    @Test
    public void 뱃지_맴버아이디로_조회(){
        Long testMemberId = 30L;
        List<Badge> badges = badgeRepository.findByMemberId(testMemberId).get();
        System.out.println("badges = " + badges);
    }

}
