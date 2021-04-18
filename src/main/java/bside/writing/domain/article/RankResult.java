package bside.writing.domain.article;

import lombok.Getter;

@Getter
public class RankResult<T,S>{
    T member;
    S count;

    public RankResult(T first, S second) {
        this.member = first;
        this.count = second;
    }

    static <T,S> RankResult<T,S> add(T first, S second){
        return new RankResult<T,S>(first, second);
    }
}
