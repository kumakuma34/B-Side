package bside.writing.enums;

import java.util.Arrays;
import java.util.List;

public enum BadgeCode {
    //code명 + badge 획득 기준값

    // article 제출 횟수 badge
    ARTICLE_COMMIT(Arrays.asList("1","10","50"))
    // challenge 참여 badge
    ,CHALLENGE_PARTICIPATE(Arrays.asList("1","6","12"))
    // challenge 완주 badge
    ,CHALLENGE_FINISH(Arrays.asList("1","2","8"))
    // like 획득 badge
    ,LIKE_COUNT(Arrays.asList("1","10","20"))
    ;

    private List<String> criteria;

    BadgeCode(List<String> criteria) {
        this.criteria = criteria;
    }

    public List<String> getCriteria() {
        return criteria;
    }
}