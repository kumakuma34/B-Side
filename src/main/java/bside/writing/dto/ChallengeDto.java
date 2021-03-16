package bside.writing.dto;

import bside.writing.Entity.Challenge;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.Column;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
public class ChallengeDto {

    @Getter
    @Setter
    public static class Request{
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

        public Request(Request request){
            this.cover_img = request.getCover_img();
            this.challenge_title = request.getChallenge_title();
            this.challenge_detail = request.getChallenge_detail();
            this.max_participant = request.getMax_participant();
            this.current_participant = request.getCurrent_participant();
            this.start_dt = request.getStart_dt();
            this.duration = request.getDuration();
            this.submit_days_cnt = request.getSubmit_days_cnt();
            this.status = request.getStatus();
            this.created_id = request.getCreated_id();
            this.modified_id = request.getModified_id();
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
