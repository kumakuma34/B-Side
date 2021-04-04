package bside.writing.dto;

import bside.writing.Repository.ThemeRepository;
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
    @AllArgsConstructor
    public static class Info{
        @JsonProperty("cover_img")
        private int coverImg;

        @JsonProperty("challenge_title")
        private String  challengeTitle;

        @JsonProperty("challenge_detail")
        private String  challengeDetail;

        @JsonProperty("max_participant")
        private int  maxParticipant;

        //private int currentParticipant;

        @JsonProperty("start_dt")
        private LocalDate startDt;

        private int duration;

        @JsonProperty("submit_days_cnt")
        private int submitDaysCnt;

        private int status;

        @JsonProperty("create_id")
        private long createdId;

        @JsonProperty("modified_id")
        private long modifiedId;
    }

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    public static class Request{
        @JsonProperty("cover_img")
        private String coverImg;

        @JsonProperty("challenge_title")
        private String  challengeTitle;

        @JsonProperty("challenge_detail")
        private String  challengeDetail;

        @JsonProperty("max_participant")
        private int  maxParticipant;

        @JsonProperty("current_participant")
        private int currentParticipant;

        @JsonProperty("start_dt")
        private LocalDate startDt;

        private int duration;

        @JsonProperty("submit_days_cnt")
        private int submitDaysCnt;

        private int status;

        private String theme_string;//#글감1 #글감2
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class GetRequest{
        private String search_type;
        private int search_count;
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
        private long theme1;
        @JsonIgnore
        private long theme2;
        @JsonIgnore
        private long theme3;
        private List<String> themeNames;
        private List<String> joinMembers;
        @JsonIgnore
        private Long ownerId;
        private String ownerName;
        private Boolean isOwner;

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
                    .theme1(this.theme1)
                    .theme2(this.theme2)
                    .theme3(this.theme3)
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
            if(entity.getTheme1() != null){
                this.theme1 = entity.getTheme1();
            }
            if(entity.getTheme2() != null){
                this.theme2 = entity.getTheme2();
            }
            if(entity.getTheme3() != null){
                this.theme3 = entity.getTheme3();
            }
        }
    }


    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    public static class SaveDto{
        private Response info;
        private long created_id;
        private long modified_id;

        public Challenge toEntity(){
            return Challenge.builder()
                    .coverImg(this.info.getCoverImg())
                    .challengeTitle(this.info.getChallengeTitle())
                    .challengeDetail(this.info.getChallengeDetail())
                    .maxParticipant(this.info.getMaxParticipant())
                    .currentParticipant(0)
                    .startDt(this.info.getStartDt())
                    .duration(this.info.getDuration())
                    .submitDaysCnt(this.info.getSubmitDaysCnt())
                    .status(0)
                    .createdId(created_id)
                    .modifiedId(modified_id)
                    .build();
        }
    }


    //legacy
    private Long challenge_id;

    private String cover_img;

    private String challenge_title;

    private String challenge_detail;

    private int  max_participant;

    private int current_participant;

    private LocalDate start_dt;

    private int duration;

    private int submit_days_cnt;

    private int status;

    private long created_id;

    private long modified_id;

    public ChallengeDto(ThemeRepository themeRepository, Challenge entity){
        this.challenge_id = entity.getChallengeId();
        this.cover_img = entity.getCoverImg();
        this.challenge_title = entity.getChallengeTitle();
        this.challenge_detail = entity.getChallengeDetail();
        this.max_participant = entity.getMaxParticipant();
        this.current_participant = entity.getCurrentParticipant();
        this.start_dt = entity.getStartDt();
        this.duration = entity.getDuration();
        this.submit_days_cnt = entity.getSubmitDaysCnt();
        this.status = entity.getStatus();
        this.created_id = entity.getCreatedId();
        this.modified_id = entity.getModifiedId();
    }


}
