package bside.writing.dto;

import bside.writing.domain.challenge.Challenge;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
public class ChallengeDto {

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Info{
        private int cover_img;
        private String  challenge_title;
        private String  challenge_detail;
        private int  max_participant;
        //private int current_participant;
        private LocalDate start_dt;
        private int duration;
        private int submit_days_cnt;
        private int status;
        private long created_id;
        private long modified_id;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Request{
        private Info info;
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
                    .cover_img(this.info.getCover_img())
                    .challenge_title(this.info.getChallenge_title())
                    .challenge_detail(this.info.getChallenge_detail())
                    .max_participant(this.info.getMax_participant())
                    .current_participant(0)
                    .start_dt(this.info.getStart_dt())
                    .duration(this.info.getDuration())
                    .submit_days_cnt(this.info.getSubmit_days_cnt())
                    .status(this.info.getStatus())
                    .created_id(this.getCreated_id())
                    .modified_id(this.getModified_id())
                    .build();
        }

    }

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
        this.challenge_id = entity.getChallenge_id();
        this.cover_img = entity.getCover_img();
        this.challenge_title = entity.getChallenge_title();
        this.challenge_detail = entity.getChallenge_detail();
        this.max_participant = entity.getMax_participant();
        this.current_participant = entity.getCurrent_participant();
        this.start_dt = entity.getStart_dt();
        this.duration = entity.getDuration();
        this.submit_days_cnt = entity.getSubmit_days_cnt();
        this.status = entity.getStatus();
        this.created_id = entity.getCreated_id();
        this.modified_id = entity.getModified_id();
    }

    public Challenge toEntity(){
        return Challenge.builder()
                .challenge_id(this.getChallenge_id())
                .cover_img(this.getCover_img())
                .challenge_title(this.getChallenge_title())
                .challenge_detail(this.getChallenge_detail())
                .max_participant(this.getMax_participant())
                .current_participant(this.getCurrent_participant())
                .start_dt(this.getStart_dt())
                .duration(this.getDuration())
                .submit_days_cnt(this.getSubmit_days_cnt())
                .status(this.getStatus())
                .created_id(this.getCreated_id())
                .modified_id(this.getModified_id())
                .build();
    }
}
