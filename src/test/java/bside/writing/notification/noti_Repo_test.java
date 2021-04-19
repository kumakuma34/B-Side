package bside.writing.notification;

import bside.writing.Repository.NotificationRepository;
import bside.writing.dto.NotificationSaveDto;
import bside.writing.enums.NotiType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;


@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class noti_Repo_test {

    @Autowired
    NotificationRepository notificationRepository;

    @Test
    public void save_wellcom_noti() {
        NotificationSaveDto notificationDto = NotificationSaveDto.builder()
                .memberId(30L)
                .notiType(NotiType.WELLCOM)
                .build();
        notificationRepository.save(notificationDto.toEntity());
    }
}
