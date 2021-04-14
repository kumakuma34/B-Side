package bside.writing.badge.badgeService;

import bside.writing.Repository.BadgeRepository;
import bside.writing.Service.BadgeService;
import bside.writing.dto.BadgeDto;
import bside.writing.enums.BadgeCode;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

@SpringBootTest
@Transactional
public class badgeServiceTest {
    @Autowired
    BadgeService badgeService;

    @Test
    void 기본뱃지응답생성(){
        System.out.println(badgeService.getDefaultResponse());

    }

    @Test
    void 뱃지_조건_충족_여부함수(){
        BadgeDto badgeDto = BadgeDto.builder()
                .memberId(1L)
                .badgeCode(BadgeCode.CHALLENGE_FINISH.name())
                .badgeValue("1") // 1,2,8
                .build();
        boolean result = badgeService.isAchieve(badgeDto);
        Assertions.assertThat(result).isTrue();
    }

    @Test
    void 뱃지_조건_충족_여부함수2(){
        BadgeDto badgeDto = BadgeDto.builder()
                .memberId(1L)
                .badgeCode(BadgeCode.CHALLENGE_FINISH.name())
                .badgeValue("3") // 1,2,8
                .build();
        boolean result = badgeService.isAchieve(badgeDto);
        Assertions.assertThat(result).isFalse();
    }

    @Test
    void 뱃지_확인_후_생성(){
        BadgeDto badgeDto = BadgeDto.builder()
                .memberId(1L)
                .badgeCode(BadgeCode.CHALLENGE_FINISH.name())
                .badgeValue("1") // 1,2,8
                .build();

        boolean has = badgeService.hasThisBadge(badgeDto);
        Assertions.assertThat(has).isFalse();

        badgeService.checkAndGetBadge(badgeDto);

        has = badgeService.hasThisBadge(badgeDto);
        Assertions.assertThat(has).isTrue();

        BadgeDto duplicateResult = badgeService.checkAndGetBadge(badgeDto);
        Assertions.assertThat(duplicateResult).isNull();
    }
}
