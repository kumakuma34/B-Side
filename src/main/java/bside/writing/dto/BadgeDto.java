package bside.writing.dto;

import bside.writing.domain.badge.Badge;
import bside.writing.enums.BadgeCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class BadgeDto{

    private Long badgeId;

    private Long memberId;

    private BadgeCode badgeCode;

    private int badgeCurValue;

    private int badgeMaxValue;

    private String badgeUrl;

    public BadgeDto(Badge entity) {
        this.badgeId = entity.getBadgeId();
        this.memberId = entity.getMemberId();
        this.badgeCode = BadgeCode.valueOf(entity.getBadgeCode());
        this.badgeCurValue = entity.getBadgeCurValue();
        this.badgeMaxValue = entity.getBadgeMaxValue();
    }

    public Badge toEntity(){
        return Badge.builder()
                .badgeId(badgeId)
                .memberId(memberId)
                .badgeCode(badgeCode.name())
                .badgeCurValue(badgeCurValue)
                .badgeMaxValue(badgeMaxValue)
                .build();
    }
}
