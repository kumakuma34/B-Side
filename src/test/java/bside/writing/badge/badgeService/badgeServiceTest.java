package bside.writing.badge.badgeService;

import bside.writing.Service.BadgeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class badgeServiceTest {
    @Autowired
    BadgeService badgeService;

    @Test
    void 기본뱃지응답생성(){
        System.out.println(badgeService.getDefaultResponse());

    }
}
