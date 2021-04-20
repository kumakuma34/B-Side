package bside.writing.domain.article;

import bside.writing.templateClass.BaseTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "article_like")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArticleLike extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name ="article_like_id")
    private Long articleLikeId;

    @Column(name = "article_id")
    private Long articleId;

    @Column(name = "member_id")
    private Long memberId;

}
