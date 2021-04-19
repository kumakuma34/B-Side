package bside.writing.dto;

import bside.writing.domain.notification.Notification;
import bside.writing.enums.NotiTargetType;
import bside.writing.enums.NotiType;
import io.jsonwebtoken.lang.Assert;
import lombok.Builder;

import java.time.LocalDateTime;

public class NotificationSaveDto {

    private Long memberId;
    private NotiType notiType;
    private String notiMessage; 
    private LocalDateTime notiStartDate;
    private NotiTargetType fromType;
    private Long fromId;

    @Builder
    public NotificationSaveDto(Long memberId, NotiType notiType, LocalDateTime notiStartDate, Long fromId){
        Assert.notNull(memberId, "memberid must not be null");
        Assert.notNull(notiType, "notiType must not be null");
        if(!notiType.getTargetType().equals(NotiTargetType.NA)) {
            Assert.notNull(fromId, "fromId must not be null");
        }
        this.memberId = memberId;
        this.notiType = notiType;
        this.fromId = fromId;

        this.notiStartDate = notiStartDate;
        this.fromType = notiType.getTargetType();
        this.notiMessage = notiType.getMsg();
    }

    public Notification toEntity(){
        return Notification.builder()
                .memberId(memberId)
                .notiType(notiType.name())
                .notiMessage(notiMessage)
                .notiRead(false)
                .notiStartDate(notiStartDate)
                .fromType(fromType.name())
                .fromId(fromId)
                .build();
    }
}
