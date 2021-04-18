package bside.writing.dto;

import bside.writing.domain.badge.Badge;
import bside.writing.enums.BadgeCode;
import io.jsonwebtoken.lang.Assert;
import lombok.Builder;
import lombok.Getter;

@Getter
public class BadgeSaveDto {

    private Long memberId;

    private BadgeCode badgeCode;

    private String badgeValue;

    @Builder
    public BadgeSaveDto(Long memberId, BadgeCode badgeCode, String badgeValue) {
        Assert.notNull(memberId, "memberid must not be null");
        Assert.notNull(badgeCode, "badgeCode must not be null");
        Assert.notNull(badgeValue, "badgeValue must not be null");

        this.memberId = memberId;
        this.badgeCode = badgeCode;
        this.badgeValue = badgeValue;
    }

    public Badge toEntity(){
        return Badge.builder()
                .memberId(memberId)
                .badgeCode(badgeCode.name())
                .badgeValue(badgeValue)
                .build();
    }
}
