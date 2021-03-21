package bside.writing.dto;

import bside.writing.domain.badge.Badge;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
@AllArgsConstructor
public class BadgeDto {

    private Long badgeId;

    private Long memberId;

    @JsonProperty("badge_code")
    private String badgeCode;

    private String badgeValue;

    public BadgeDto(Badge entity) {
        this.badgeId = entity.getBadgeId();
        this.memberId = entity.getMemberId();
        this.badgeCode = entity.getBadgeCode();
        this.badgeValue = entity.getBadgeValue();
    }

    public Badge toEntity(){
        return Badge.builder()
                .badgeId(badgeId)
                .memberId(memberId)
                .badgeCode(badgeCode)
                .badgeValue(badgeValue)
                .build();
    }
}
