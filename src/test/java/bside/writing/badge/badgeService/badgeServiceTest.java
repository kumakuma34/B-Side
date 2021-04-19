package bside.writing.badge.badgeService;

import bside.writing.Service.BadgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

@SpringBootTest
@Transactional
public class badgeServiceTest {
    @Autowired
    BadgeService badgeService;

}
