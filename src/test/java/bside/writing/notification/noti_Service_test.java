package bside.writing.notification;

import bside.writing.Service.NotificationService;
import bside.writing.domain.notification.Notification;
import bside.writing.dto.NotificationDto;
import bside.writing.enums.NotiType;
import org.assertj.core.api.Assertions;
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
        List<NotificationDto> memberNotification = notificationService.getNotification(30L);
        for (NotificationDto noti : memberNotification) {
            System.out.println("noti = " + noti);
        }
    }


}
