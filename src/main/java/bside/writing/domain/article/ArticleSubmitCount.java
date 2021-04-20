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
//TODO : 얘네 DTO로 옮겨서 수정, 엔티티도아닌데 나와있어서 이상함
