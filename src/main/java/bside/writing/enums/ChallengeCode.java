package bside.writing.enums;

import bside.writing.dto.ChallengeDto;
import lombok.Getter;

@Getter
public enum ChallengeCode {
    DEFAULT_SEARCH_COUNT(10);

    private int val;
    ChallengeCode(int i){this.val = i;}
}
