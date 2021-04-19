package bside.writing.dto;

import bside.writing.domain.badge.Badge;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class BadgeResponseDto{

    @JsonIgnore
    private Long badgeId;

    @JsonIgnore
    private Long memberId;

    @JsonProperty("badge_code")
    private String badgeCode;

    @JsonProperty("badge_value")
    private int badgeValue;

    @JsonProperty("badge_url")
    private String badgeUrl;

    public BadgeResponseDto(BadgeSaveDto saveDto) {
        this.memberId = saveDto.getMemberId();
        this.badgeCode = saveDto.getBadgeCode().name();
        this.badgeValue = saveDto.getBadgeValue();
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
