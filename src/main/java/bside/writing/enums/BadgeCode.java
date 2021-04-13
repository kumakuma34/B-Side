package bside.writing.enums;

import java.util.Arrays;
import java.util.List;

public enum BadgeCode {
    ARTICLE_COMMIT(Arrays.asList("1","10","50"))
    ,CHALLENGE_PARTICIPATE(Arrays.asList("1","6","12"))
    ,CHALLENGE_FINISH(Arrays.asList("1","2","8"))
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