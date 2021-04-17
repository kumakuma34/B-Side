package bside.writing.enums;

import com.google.common.collect.Maps;
import com.sun.istack.Nullable;
import lombok.Getter;

import java.util.Arrays;
import java.util.Map;

@Getter
public enum ArticleStatusCode {
    VISIBLE(0),
    NON_VISIBLE(1),
    TEMP(2);

    private static final Map<Integer, ArticleStatusCode> LOOKUP = Maps.uniqueIndex(
            Arrays.asList(ArticleStatusCode.values()),
            ArticleStatusCode::getStatus
    );

    private int val;
    ArticleStatusCode(int i){this.val = i;}
    public int getStatus(){
        return val;
    }
    @Nullable
    public static ArticleStatusCode fromStatus(int status){
        return LOOKUP.get(status);
    }
}
