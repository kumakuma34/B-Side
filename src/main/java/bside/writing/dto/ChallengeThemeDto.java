package bside.writing.dto;

import bside.writing.domain.challenge.ChallengeTheme;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

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

    private String theme_name;

    public ChallengeThemeDto(ChallengeTheme entity){
        this.challenge_theme_id = entity.getChallenge_theme_id();
        this.challenge_id = entity.getChallenge_id();
        this.theme_name = entity.getTheme_name();
    }

    public ChallengeTheme toEntity(){
        return ChallengeTheme.builder()
                .challenge_theme_id(this.getChallenge_theme_id())
                .challenge_id(this.getChallenge_id())
                .theme_name(this.getTheme_name())
                .build();
    }
}
