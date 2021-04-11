package bside.writing.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public class ArticleDto {
    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    public static class Request{
        @JsonProperty("article_title")
        private String articleTitle;
        @JsonProperty("article_detail")
        private String articleDetail;
        @JsonProperty("challenge_id")
        private Long challengeId;
        @JsonProperty("visible")
        private String visible;

    }
}
