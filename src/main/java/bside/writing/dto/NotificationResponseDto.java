package bside.writing.dto;

import bside.writing.domain.notification.Notification;
import bside.writing.enums.NotiTargetType;
import bside.writing.enums.NotiType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.util.LinkedHashMap;
import java.util.Map;

@Setter
@Getter
@Builder
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NotificationResponseDto {

    @JsonIgnore
    NotiType notiType;

    @JsonProperty("notification_message")
    private String notiMessage;

    @JsonProperty("notification_read")
    private Boolean notiRead;

    @JsonIgnore
    private String fromType;

    @JsonIgnore
    private Long fromId;

    @JsonProperty("target")
    private Map<String, Object> target;

    public NotificationResponseDto(Notification entity) {
        this.notiType = NotiType.valueOf(entity.getNotiType());
        this.notiMessage = entity.getNotiMessage();
        this.notiRead = entity.getNotiRead();
        this.fromId = entity.getFromId();
        this.fromType = entity.getFromType();

        if(!notiType.getTargetType().equals(NotiTargetType.NA)){
            target = new LinkedHashMap<>();
            target.put("type", fromType);
            target.put("id", fromId);
        }
    }
}
