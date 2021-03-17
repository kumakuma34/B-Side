package bside.writing.domain.challenge;

import bside.writing.templateClass.BaseTimeEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "challenge")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Challenge extends BaseTimeEntity {
    @javax.persistence.Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long challenge_id;

    @Column @NonNull
    private int cover_img;

    @Column(length = 50) @NonNull
    private String  challenge_title;

    @Column(length = 100) @NonNull
    private String  challenge_detail;

    @Column @NonNull
    private int  max_participant;

    @Column
    private int current_participant;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    @Column @NonNull
    private LocalDate start_dt;

    @Column @NonNull
    private int duration;

    @Column @NonNull
    private int submit_days_cnt;

    @Column(length = 10)
    private int status;

    @Column @NonNull
    private long created_id;

    @Column @NonNull
    private long modified_id;

    //public Challenge(){};//default Constructor
}