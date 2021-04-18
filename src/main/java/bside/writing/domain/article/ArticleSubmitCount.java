package bside.writing.domain.article;

import lombok.Getter;

@Getter
public class ArticleSubmitCount {
    private Long memberId;
    private Long submitCnt;

    public ArticleSubmitCount(Long memberId , Long submitCnt){
        this.memberId = memberId;
        this.submitCnt = submitCnt;
    }
}
