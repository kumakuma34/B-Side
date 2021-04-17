package bside.writing.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import javassist.LoaderClassPath;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.apache.tomcat.jni.Local;

import java.sql.Timestamp;
import java.time.LocalDateTime;

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
        @JsonProperty("status")
        private int status;

    }

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    public static class ResponseAsList{
        @JsonProperty("article_id")
        private Long articleId;
        @JsonProperty("article_title")
        private String articleTitle;
        @JsonProperty("challenge_title")
        private String challengeTitle;
        private int week;
        @JsonProperty("submit_cnt")
        private int submitCnt;
        @JsonProperty("created_date")
        private LocalDateTime createdDate;
        @JsonProperty("status")
        private String status;
    }

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    public static class TempArticleResponse{
        @JsonProperty("article_id")
        private Long articleId;
        @JsonProperty("article_title")
        private String articleTitle;
        @JsonProperty("created_date")
        private LocalDateTime createDate;
        @JsonProperty("article_detail")
        private String articleDetail;
    }

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    public static class SubmitArticleResponse{
        private int week;
        @JsonProperty("submit_cnt")
        private int submitCnt;
        @JsonProperty("challenge_title")
        private String challengeTitle;
        private String author;
        private int likeCnt;
        @JsonProperty("article_id")
        private Long articleId;
        @JsonProperty("article_title")
        private String articleTitle;
        @JsonProperty("created_date")
        private LocalDateTime createDate;
        @JsonProperty("article_detail")
        private String articleDetail;
    }
}
