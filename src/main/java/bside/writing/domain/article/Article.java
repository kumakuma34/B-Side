package bside.writing.domain.article;

import bside.writing.templateClass.BaseTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "article")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Article extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name ="article_id")
    private Long articleId;

    @Column(name = "article_title", length = 100)
    private String articleTitle;

    @Column(name = "article_detail" , columnDefinition = "TEXT")
    private String articleDetail;

    @Column(name = "created_id")
    private Long createdId;

    @Column(name = "challenge_id")
    private Long challengeId;

    @Column(name = "week")
    private int week;

    @Column(name ="submit_cnt")
    private int submitCnt;

    @Column(name = "status" , length = 1)
    private String status;
}
