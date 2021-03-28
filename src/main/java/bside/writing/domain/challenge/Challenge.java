package bside.writing.domain.challenge;

import bside.writing.templateClass.BaseTimeEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.annotations.Formula;
import org.springframework.web.client.HttpClientErrorException;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

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

    @Column(name = "cover_img")
    private int coverImg;

    @Column(name = "challenge_title", length = 50)
    private String  challengeTitle;

    @Column(name = "challenge_detail", length = 100)
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

    @Column
    private Long theme1;

    @Column
    private Long theme2;

    @Column
    private Long theme3;

    @Column(name = "created_id")
    private long createdId;

    @Column(name = "modified_id")
    private long modifiedId;
    //public Challenge(){};//default Constructor

//    @Formula(value = "select DATE_ADD(c.startDt, INTERVAL c.duration * 7 DAY) from challenge")
//    private LocalDateTime endDt;

    public void increaseCurrentParticipant(){
        if(this.currentParticipant >= this.maxParticipant)
            throw new IllegalArgumentException("challenge participant full");
        this.currentParticipant++;
    }

    @Override
    public String toString() {
        return "Challenge{" +
                "challengeId=" + challengeId +
                ", coverImg=" + coverImg +
                ", challengeTitle='" + challengeTitle + '\'' +
                ", challengeDetail='" + challengeDetail + '\'' +
                ", maxParticipant=" + maxParticipant +
                ", currentParticipant=" + currentParticipant +
                ", startDt=" + startDt +
                ", duration=" + duration +
                ", submitDaysCnt=" + submitDaysCnt +
                ", status=" + status +
                ", theme1=" + theme1 +
                ", theme2=" + theme2 +
                ", theme3=" + theme3 +
                ", createdId=" + createdId +
                ", modifiedId=" + modifiedId +
                '}';
    }
}
