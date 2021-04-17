package bside.writing.dto;

import bside.writing.domain.notification.Notification;
import bside.writing.enums.NotiType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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

    @JsonIgnore
    private Long fromId;

    @JsonIgnore
    private String fromName;

    @JsonProperty("link_target")
    private Map<String, Object> target = new LinkedHashMap<>();


    public NotificationDto(Notification entity) {
        this.id = entity.getId();
        this.memberId = entity.getMemberId();
        this.notiType = NotiType.valueOf(entity.getNotiType());
        this.notiMessage = entity.getNotiMessage();
        this.notiRead = entity.getNotiRead();
        this.notiStartDate = entity.getNotiStartDate();
        this.fromId = entity.getFromId();
        this.fromName = entity.getFromName();

        target.put("id", fromId);
        target.put("name", fromName);
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
