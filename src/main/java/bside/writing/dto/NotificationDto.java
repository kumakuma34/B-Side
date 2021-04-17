package bside.writing.dto;

import bside.writing.domain.notification.Notification;
import bside.writing.enums.NotiType;
import bside.writing.templateClass.Entityable;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@Builder
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NotificationDto{

    @JsonIgnore
    private Long id;

    @JsonIgnore
    private Long memberId;

    @JsonProperty("notification_type")
    NotiType notiType;

    @JsonProperty("notification_message")
    private String notiMessage;

    @JsonProperty("notification_read")
    private Boolean notiRead;

    @JsonIgnore
    private LocalDateTime notiStartDate;

    @JsonProperty("from_id")
    private Long fromId;

    @JsonProperty("from_name")
    private String fromName;

    public NotificationDto(Notification entity) {
        this.id = entity.getId();
        this.memberId = entity.getMemberId();
        this.notiType = NotiType.valueOf(entity.getNotiType());
        this.notiMessage = entity.getNotiMessage();
        this.notiRead = entity.getNotiRead();
        this.notiStartDate = entity.getNotiStartDate();
        this.fromId = entity.getFromId();
        this.fromName = entity.getFromName();
    }

    public Notification toEntity(){
        return Notification.builder()
                .id(id)
                .memberId(memberId)
                .notiType(notiType.name())
                .notiMessage(notiMessage)
                .notiRead(notiRead)
                .notiStartDate(notiStartDate)
                .fromId(fromId)
                .fromName(fromName)
                .build();
    }

}
