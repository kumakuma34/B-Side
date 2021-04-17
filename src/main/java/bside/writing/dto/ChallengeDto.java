package bside.writing.dto;

import bside.writing.Repository.ThemeRepository;
import bside.writing.Service.ChallengeService;
import bside.writing.domain.challenge.Challenge;
import bside.writing.domain.theme.Theme;
import bside.writing.enums.ChallengeStatusCode;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

public class ChallengeDto {

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    public static class Request{
        private Long challengeId;
        @JsonProperty("cover_img")
        private String coverImg;

        @JsonProperty("challenge_title")
        private String  challengeTitle;

        @JsonProperty("challenge_detail")
        private String  challengeDetail;

        @JsonProperty("max_participant")
        private int  maxParticipant;

        @JsonProperty("start_dt")
        private LocalDate startDt;

        private int duration;

        @JsonProperty("submit_days_cnt")
        private int submitDaysCnt;

        private String theme;//"글감1, 글감2, 글감3"

    }

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Response {
        private Long challengeId;
        private String coverImg;
        private String  challengeTitle;
        private String  challengeDetail;
        private int  maxParticipant;
        private int currentParticipant;
        private LocalDate startDt;
        private int duration;
        private int submitDaysCnt;
        @JsonIgnore
        private int status;
        private String status_name;
        @JsonIgnore
        private long createdId;
        @JsonIgnore
        private long modifiedId;
        @JsonIgnore
        private String theme;
        private List<String> themeNames;
        private List<String> joinMembers;
        @JsonIgnore
        private Long ownerId;
        private String ownerName;
        private Boolean isOwner;
        @JsonProperty("achievement_rate")
        private int achievementRate;

        public String makeThemeString(){
            String theme = "";
            for(int i = 0 ; i < themeNames.size(); i ++){
                theme += themeNames.get(i);
                if(i != themeNames.size() -1)
                    theme += ", ";
            }
            return theme;
        }
        public Challenge toEntity(){
            return Challenge.builder()
                    .challengeId(this.challengeId)
                    .coverImg(this.coverImg)
                    .challengeTitle(this.challengeTitle)
                    .challengeDetail(this.challengeDetail)
                    .maxParticipant(this.maxParticipant)
                    .currentParticipant(this.currentParticipant)
                    .startDt(this.startDt)
                    .duration(this.duration)
                    .submitDaysCnt(this.submitDaysCnt)
                    .status(this.status)
                    .createdId(this.createdId)
                    .modifiedId(this.modifiedId)
                    .theme(this.theme)
                    .build();
        }

        public Response(Challenge entity){ //Response
            this.challengeId = entity.getChallengeId();
            this.coverImg = entity.getCoverImg();
            this.challengeTitle = entity.getChallengeTitle();
            this.challengeDetail = entity.getChallengeDetail();
            this.maxParticipant = entity.getMaxParticipant();
            this.currentParticipant = entity.getCurrentParticipant();
            this.startDt = entity.getStartDt();
            this.duration = entity.getDuration();
            this.submitDaysCnt = entity.getSubmitDaysCnt();
            this.status = entity.getStatus();
            if(entity.getStatus() == 0) this.status_name = ChallengeStatusCode.RECRUITING.name();
            else if(entity.getStatus() == 1) this.status_name = ChallengeStatusCode.IN_PROGRESS.name();
            else if(entity.getStatus() == 2) this.status_name = ChallengeStatusCode.COMPLETE.name();
            this.createdId = entity.getCreatedId();
            this.modifiedId = entity.getModifiedId();
            this.theme = entity.getTheme();
        }
    }

}
