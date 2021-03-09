package bside.writing.Entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "challenge")
@Data
public class Challenge {
    @javax.persistence.Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY) @NonNull
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

    @CreationTimestamp @NonNull
    private LocalDateTime reg_dt;

    @Column @NonNull
    private long reg_id;

    @UpdateTimestamp @NonNull
    private LocalDateTime upd_dt;

    @Column @NonNull
    private long upd_id;

}
