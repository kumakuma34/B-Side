package bside.writing.Entity;

import bside.writing.domain2.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "challenge")
@Data
@Builder
public class Challenge extends BaseTimeEntity {
    @javax.persistence.Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long challenge_id;

    @Column(length = 255) @NonNull
    private String cover_img;

    @Column(length = 20) @NonNull
    private String  writing_theme;

    @Column(length = 50) @NonNull
    private String  challenge_theme;

    @Column(length = 100) @NonNull
    private String  challenge_detail;

    @Column @NonNull
    private int  max_participant;

    @Column
    private int current_participant;

    @Column @NonNull
    private LocalDateTime start_dt;

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

}
