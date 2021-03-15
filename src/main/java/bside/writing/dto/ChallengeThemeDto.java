package bside.writing.dto;

import bside.writing.Entity.Challenge;
import bside.writing.Entity.ChallengeTheme;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ChallengeThemeDto {
    private Long challenge_theme_id;

    private Long challenge_id;

    private Long theme_id;

    public ChallengeThemeDto(ChallengeTheme entity){
        this.challenge_theme_id = entity.getChallenge_theme_id();
        this.challenge_id = entity.getChallenge_id();
        this.theme_id = entity.getTheme_id();
    }

    public ChallengeTheme toEntity(){
        return ChallengeTheme.builder()
                .challenge_theme_id(this.getChallenge_theme_id())
                .challenge_id(this.getChallenge_id())
                .theme_id(this.getTheme_id())
                .build();
    }
}
