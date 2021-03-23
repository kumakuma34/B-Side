package bside.writing.dto;

import bside.writing.domain.challenge.Challenge;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

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
    @Builder
    @AllArgsConstructor
    public static class AllInfo{
        private int coverImg;
        private String  challengeTitle;
        private String  challengeDetail;
        private int  maxParticipant;
        private int currentParticipant;
        private LocalDate startDt;
        private int duration;
        private int submitDaysCnt;
        private int status;
        private long createdId;
        private long modifiedId;
        private List<String> theme_string;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Request{
        private Info challenge_info;
        private String theme_string;//#글감1 #글감2 #글감3
    }

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    public static class SaveDto{
        private Info info;
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
                    .status(this.info.getStatus())
                    .createdId(created_id)
                    .modifiedId(modified_id)
                    .build();
        }
    }


    //legacy
    private Long challenge_id;

    private int cover_img;

    private String  challenge_title;

    private String  challenge_detail;

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
