package bside.writing.Entity;

import bside.writing.domain2.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "challenge_theme")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChallengeTheme extends BaseTimeEntity {
    @javax.persistence.Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long challenge_theme_id;

    @Column
    private Long challenge_id;

    @Column
    private Long theme_id;

    //public Challenge(){};//default Constructor
}
