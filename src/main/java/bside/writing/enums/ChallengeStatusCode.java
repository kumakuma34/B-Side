package bside.writing.enums;

public enum ChallengeStatusCode {
    //모집 중(시작 전) : 0
    //진행 중 : 1
    //완료 됨 : 2

    RECRUITING(0),
    IN_PROGRESS(1),
    COMPLETE(1);

    private int val;
    ChallengeStatusCode(int i){this.val = i;}
}
