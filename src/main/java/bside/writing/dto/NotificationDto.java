package bside.writing.dto;

import bside.writing.domain.notification.Notification;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class NotificationDto {
    private Long id;
    private Long memberId;
    private Long fromMemberId;
    private Long fromArticleId;
    private Long fromChallengeId;
    private String notiType;
    private String notiMessage;
    private String notiUrl;
    private Boolean notiRead;
    private LocalDateTime reserveDate;

    public LocalDateTime getReserveDate() {
        return reserveDate == null ? null : reserveDate;
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
