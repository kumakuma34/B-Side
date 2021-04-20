package bside.writing.badge.badgeService;

import bside.writing.Service.BadgeService;
import bside.writing.enums.BadgeCode;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class badgeServiceTest {

    @Autowired
    private BadgeService badgeService;

    @Test
    public void search_badge(){
        Long memberId = 30L;
        Map<String, List> stringListMap = badgeService.makeResponse(memberId);
        System.out.println("stringListMap = " + stringListMap);
    }

    @Test
    public void increase_badge_cur_value(){
        Long memberId = 30L;
        //badgeService.increaseBadgeCurValue(memberId, BadgeCode.ARTICLE_COMMIT);
        //badgeService.increaseBadgeCurValue(memberId, BadgeCode.ARTICLE_COMMIT);

    }

}
