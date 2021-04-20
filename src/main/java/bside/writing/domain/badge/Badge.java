package bside.writing.domain.badge;

import bside.writing.templateClass.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "badge")
@Getter
@Entity
public class Badge extends BaseTimeEntity {

    @Id
    @Column(name = "badge_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long badgeId;

    @Column(name = "member_id", nullable = false)
    private Long memberId;

    @Column(name = "badge_code", nullable = false)
    private String badgeCode;

    @Column(name = "badge_cur_value", nullable = false)
    private int badgeCurValue;

    @Column(name = "badge_max_value", nullable = false)
    private int badgeMaxValue;

    public void increaseBadgeCurValue(){
        badgeCurValue++;
    }

    public void decreaseBadgeCurValue(){
        badgeCurValue--;
    }

    public void setBadgeMaxValue(int value){
        badgeMaxValue = value;
    }

}
