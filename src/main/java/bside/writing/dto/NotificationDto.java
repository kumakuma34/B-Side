package bside.writing.dto;

import bside.writing.domain.notification.Notification;
import bside.writing.templateClass.Entityable;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class NotificationDto implements Entityable {
    @JsonIgnore
    private Long id;

    @JsonIgnore
    private Long memberId;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("from_member_name")
    private Long fromMemberId;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("from_article_name")
    private Long fromArticleId;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("from_challenge_name")
    private Long fromChallengeId;

    @JsonProperty("notification_type")
    private String notiType;

    @JsonProperty("notification_message")
    private String notiMessage;

    @JsonProperty("notification_url")
    private String notiUrl;

    @JsonProperty("notification_read")
    private Boolean notiRead;

    @JsonIgnore
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private LocalDateTime reserveDate;

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    public static class Challenge implements Entityable {
        private Long memberId;
        private Long fromChallengeId;
        private String notiType;
        private String notiMessage;
        private String notiUrl;
        private Boolean notiRead;
        private LocalDateTime reserveDate;

        public Notification toEntity() {
            return Notification.builder()
                    .memberId(memberId)
                    .fromChallengeId(fromChallengeId)
                    .notiType(notiType)
                    .notiMessage(notiMessage)
                    .notiUrl(notiUrl)
                    .notiRead(notiRead)
                    .reserveDate(reserveDate)
                    .build();
        }
    }



    public NotificationDto(Notification entity) {
        this.id = entity.getId();
        this.memberId = entity.getMemberId();
        this.fromMemberId = entity.getFromMemberId();
        this.fromArticleId = entity.getFromArticleId();
        this.fromChallengeId = entity.getFromChallengeId();
        this.notiType = entity.getNotiType();
        this.notiMessage = entity.getNotiMessage();
        this.notiUrl = entity.getNotiUrl();
        this.notiRead = entity.getNotiRead();
        this.reserveDate = entity.getReserveDate();
    }

    public Notification toEntity(){
        return Notification.builder()
                .id(id)
                .memberId(memberId)
                .fromMemberId(fromMemberId)
                .fromArticleId(fromArticleId)
                .fromChallengeId(fromChallengeId)
                .notiType(notiType)
                .notiMessage(notiMessage)
                .notiUrl(notiUrl)
                .notiRead(notiRead)
                .reserveDate(reserveDate)
                .build();
    }


}
