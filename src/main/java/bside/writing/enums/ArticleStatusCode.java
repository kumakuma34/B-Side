package bside.writing.enums;

import lombok.Getter;

@Getter
public enum ArticleStatusCode {
    VISIBLE(0),
    NON_VISIBLE(1),
    TEMP(2);

    private int val;
    ArticleStatusCode(int i){this.val = i;}
}
