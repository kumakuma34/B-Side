package bside.writing.domain.challenge;

import bside.writing.templateClass.BaseTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "challenge_member")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChallengeMember extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name ="challenge_member_id")
    private Long challengeMemberId;

    @Column(name = "challenge_id")
    private Long challengeId;

    @Column(name = "member_id")
    private Long memberId;

    @Column(name = "submit_article_cnt")
    private Long submitArticleCnt;

    public void increaseSubmitCnt(){
        this.submitArticleCnt++;
    }
}
