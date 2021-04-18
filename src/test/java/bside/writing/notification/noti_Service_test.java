package bside.writing.notification;

import bside.writing.Service.NotificationService;

import bside.writing.dto.NotificationResponseDto;
import bside.writing.dto.NotificationSaveDto;
import bside.writing.enums.NotiType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class noti_Service_test {
    @Autowired
    NotificationService notificationService;

    @Test
    public void 노티_맴버아이디로_조회_정상조건() {
        List<NotificationResponseDto> notifications = notificationService.getNotificationAsResponseDto(30L);
        for (NotificationResponseDto noti : notifications) {
            System.out.println("noti = " + noti);
        }
    }

    @Test
    public void noti_save_dto_BEFORE_START_CHALLENGE(){
        NotificationSaveDto saveDto = NotificationSaveDto.builder()
                .memberId(30L)
                .notiType(NotiType.BEFORE_START_CHALLENGE)
                .fromId(1L)
                .build();
        notificationService.save(saveDto);
    }

    @Test
    public void noti_save_dto_ARTICLE_LIKE(){
        NotificationSaveDto saveDto = NotificationSaveDto.builder()
                .memberId(30L)
                .notiType(NotiType.ARTICLE_LIKE)
                .fromId(1L)
                .build();
        notificationService.save(saveDto);
    }

    @Test
    public void noti_save_dto_WELLCOM(){
        NotificationSaveDto saveDto = NotificationSaveDto.builder()
                .memberId(30L)
                .notiType(NotiType.WELLCOM)
                .build();
        notificationService.save(saveDto);
    }

}
