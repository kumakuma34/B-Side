package bside.writing.domain.challenge;

import bside.writing.templateClass.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "challenge_theme")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChallengeTheme extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long challenge_theme_id;

    @Column
    private Long challenge_id;

    @Column
    private String theme_name;

    //public Challenge(){};//default Constructor
}
