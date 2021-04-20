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

    @Builder.Default
    private int badgeCurValue = 0;

    @Builder.Default
    private int badgeMaxValue = 0;

    @Builder
    public BadgeSaveDto(Long memberId, BadgeCode badgeCode, int badgeCurValue) {
        Assert.notNull(memberId, "memberid must not be null");
        Assert.notNull(badgeCode, "badgeCode must not be null");
        Assert.notNull(badgeCurValue, "badgeCurValue must not be null");
        this.memberId = memberId;
        this.badgeCode = badgeCode;
        this.badgeCurValue = badgeCurValue;
    }

    public Badge toEntity(){
        return Badge.builder()
                .memberId(memberId)
                .badgeCode(badgeCode.name())
                .badgeCurValue(badgeCurValue)
                .badgeMaxValue(badgeMaxValue)
                .build();
    }
}
