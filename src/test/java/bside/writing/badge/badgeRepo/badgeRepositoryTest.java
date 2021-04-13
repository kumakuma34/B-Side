package bside.writing.badge.badgeRepo;

import bside.writing.Repository.BadgeRepository;
import bside.writing.domain.badge.Badge;
import bside.writing.dto.BadgeDto;
import bside.writing.enums.BadgeCode;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.Optional;

@SpringBootTest
@Transactional
public class badgeRepositoryTest {

    @Autowired
    BadgeRepository badgeRepository;

    @Test
    void 뱃지_저장(){
        BadgeDto badgeDto = BadgeDto.builder()
                .memberId(1L)
                .badgeCode(BadgeCode.CHALLENGE_FINISH.name())
                .badgeValue("8")
                .build();
        badgeRepository.save(badgeDto.toEntity());
    }

    @Test
    void 뱃지_조회(){
        String badgeCode = BadgeCode.CHALLENGE_FINISH.name();
        Optional<Badge> byMemberIdAndBadgeCode = badgeRepository.findByMemberIdAndBadgeCodeAndBadgeValue(1L, badgeCode,"8");
        System.out.println("byMemberIdAndBadgeCode = " + byMemberIdAndBadgeCode);
    }

}
