package bside.writing.dto;

import bside.writing.Entity.Challenge;
import bside.writing.Entity.ChallengeTheme;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
public class ChallengeThemeDto {

    @Getter
    @AllArgsConstructor
    @Builder
    public static class Info{
        private Long challenge_theme_id;
        private Long challenge_id;
        private Long theme_id;

    }

    @Getter
    @Setter
    public static class Request{
        private long challenge_id;
        private long theme_id;

        public Request(Request request) {
            this.challenge_id = request.getChallenge_id();
            this.theme_id = request.getTheme_id();
        }
    }

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
