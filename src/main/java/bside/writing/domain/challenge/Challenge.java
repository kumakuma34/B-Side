package bside.writing.domain.challenge;

import bside.writing.Service.ChallengeService;
import bside.writing.dto.ChallengeDto;
import bside.writing.templateClass.BaseTimeEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.annotations.Formula;
import org.springframework.web.client.HttpClientErrorException;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "challenge")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Challenge extends BaseTimeEntity {
    @javax.persistence.Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name ="challenge_id")
    private Long challengeId;

    @Column(name = "cover_img" , length = 255)
    private String coverImg;

    @Column(name = "challenge_title", length = 50, nullable = false)
    private String  challengeTitle;

    @Column(name = "challenge_detail", length = 100, nullable = false)
    private String  challengeDetail;

    @Column(name = "max_participant")
    private int  maxParticipant;

    @Column(name = "current_participant")
    private int currentParticipant;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    @Column(name = "start_dt")
    private LocalDate startDt;

    @Column
    private int duration;
    @Column(name = "submit_days_cnt")
    private int submitDaysCnt;

    @Column(length = 10)
    private int status;

    @Column(name = "created_id")
    private long createdId;

    @Column(name = "modified_id")
    private long modifiedId;

    @Column(name = "theme", length = 100)
    private String theme;

    public void increaseCurrentParticipant(){
        if(this.currentParticipant <= this.maxParticipant)
            this.currentParticipant++;
    }
    public void update(ChallengeDto.Request request ){
        this.coverImg = request.getCoverImg();
        this.challengeTitle = request.getChallengeTitle();
        this.challengeDetail = request.getChallengeDetail();
        this.maxParticipant = request.getMaxParticipant();
        this.startDt = request.getStartDt();
        this.duration = request.getDuration();
        this.submitDaysCnt = request.getSubmitDaysCnt();
        this.theme = request.getTheme();
    }

    @Override
    public String toString() {
        return "Challenge{" +
                "challengeId=" + challengeId +
                ", coverImg='" + coverImg + '\'' +
                ", challengeTitle='" + challengeTitle + '\'' +
                ", challengeDetail='" + challengeDetail + '\'' +
                ", maxParticipant=" + maxParticipant +
                ", currentParticipant=" + currentParticipant +
                ", startDt=" + startDt +
                ", duration=" + duration +
                ", submitDaysCnt=" + submitDaysCnt +
                ", status=" + status +
                ", createdId=" + createdId +
                ", modifiedId=" + modifiedId +
                ", theme='" + theme + '\'' +
                '}';
    }
}
