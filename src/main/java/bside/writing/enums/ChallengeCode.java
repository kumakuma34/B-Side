package bside.writing.enums;

import bside.writing.dto.ChallengeDto;
import lombok.Getter;

@Getter
public enum ChallengeCode {
    DEFAULT_SEARCH_COUNT(10),
    CHALLENGE_TITLE_LENGTH(39),
    CHALLENGE_DETAIL_LENGTH(100);

    private int val;
    ChallengeCode(int i){this.val = i;}
}
