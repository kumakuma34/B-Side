package bside.writing.enums;

import lombok.Getter;

@Getter
public enum ChallengeSearchCode {
    ALL(0),
    IN(1),
    MINE(2),
    DONE(3);

    int val;

    ChallengeSearchCode(int i) {
        this.val = i;
    }

}
