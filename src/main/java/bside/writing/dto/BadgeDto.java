package bside.writing.dto;

import bside.writing.domain.badge.Badge;
import bside.writing.enums.BadgeCode;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BadgeDto{

    private Long badgeId;

    private Long memberId;

    private BadgeCode badgeCode;

    private int badgeValue;

    private String badgeUrl;

    public BadgeDto(Badge entity) {
        this.badgeId = entity.getBadgeId();
        this.memberId = entity.getMemberId();
        this.badgeCode = BadgeCode.valueOf(entity.getBadgeCode());
        this.badgeValue = entity.getBadgeValue();
    }

    public Badge toEntity(){
        return Badge.builder()
                .badgeId(badgeId)
                .memberId(memberId)
                .badgeCode(badgeCode.name())
                .badgeValue(badgeValue)
                .build();
    }
}
