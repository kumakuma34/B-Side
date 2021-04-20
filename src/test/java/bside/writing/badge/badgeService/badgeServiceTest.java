package bside.writing.badge.badgeService;

import bside.writing.Service.BadgeService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@SpringBootTest
@Transactional
public class badgeServiceTest {
    @Autowired
    BadgeService badgeService;

    @Test
    public void search_badge(){
        Long memberId = 30L;
        Map<String, List> stringListMap = badgeService.makeResponseList(memberId);
        System.out.println("stringListMap = " + stringListMap);

    }

}
