package bside.writing.enums;

import lombok.Getter;

@Getter
public enum ThemeCode {
    MAX_THEME_COUNT(3),
    MAX_THEME_LENGTH(10);

    private int val;
    ThemeCode(int i) {
        this.val = i;
    }
}
