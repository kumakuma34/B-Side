package bside.writing.dto;

import bside.writing.domain.challenge.Challenge;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
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
        private int coverImg;

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
    @Builder
    @AllArgsConstructor
    public static class AllInfo{
        private int coverImg;
        @NonNull
        private String  challengeTitle;
        @NonNull
        private String  challengeDetail;
        private int  maxParticipant;
        private int currentParticipant;
        private LocalDate startDt;
        private int duration;
        private int submitDaysCnt;
        private int status;
        private long createdId;
        private long modifiedId;
        private long theme1;
        private long theme2;
        private long theme3;

        public Challenge toEntity(){
            return Challenge.builder()
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

        public AllInfo(Challenge entity){
            this.coverImg = entity.getCoverImg();
            this.challengeTitle = entity.getChallengeTitle();
            this.challengeDetail = entity.getChallengeDetail();
            this.maxParticipant = entity.getMaxParticipant();
            this.currentParticipant = entity.getCurrentParticipant();
            this.startDt = entity.getStartDt();
            this.duration = entity.getDuration();
            this.submitDaysCnt = entity.getSubmitDaysCnt();
            this.status = entity.getStatus();
            this.createdId = entity.getCreatedId();
            this.modifiedId = entity.getModifiedId();
            if(entity.getTheme1() != null)
                this.theme1 = entity.getTheme1();
            if(entity.getTheme2() != null)
                this.theme2 = entity.getTheme2();
            if(entity.getTheme3() != null)
                this.theme3 = entity.getTheme3();
        }
    }


    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    public static class SaveDto{
        private AllInfo info;
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

    private int cover_img;

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

    public ChallengeDto(Challenge entity){
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

    public Challenge toEntity(){
        return Challenge.builder()
                .challengeId(this.getChallenge_id())
                .coverImg(this.getCover_img())
                .challengeTitle(this.getChallenge_title())
                .challengeDetail(this.getChallenge_detail())
                .maxParticipant(this.getMax_participant())
                .currentParticipant(this.getCurrent_participant())
                .startDt(this.getStart_dt())
                .duration(this.getDuration())
                .submitDaysCnt(this.getSubmit_days_cnt())
                .status(this.getStatus())
                .createdId(this.getCreated_id())
                .modifiedId(this.getModified_id())
                .build();
    }
}
