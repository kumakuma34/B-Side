package bside.writing.domain.article;

import lombok.Getter;

@Getter
public class RankResult<T,S,R>{
    T member;
    S count;
    R url;
    public RankResult(T first, S second, R third) {
        this.member = first;
        this.count = second;
        url = third;
    }

    static <T,S,R> RankResult<T,S,R> add(T first, S second, R third){
        return new RankResult<T,S,R>(first, second, third);
    }
}

//TODO : 얘네 DTO로 옮겨서 수정, 엔티티도아닌데 나와있어서 이상함
