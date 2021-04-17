package bside.writing.notification;

import bside.writing.Repository.NotificationRepository;
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
import java.util.Optional;


@SpringBootTest
@RunWith(SpringRunner.class)
//@Transactional
public class noti_Repo_test {

    @Autowired
    NotificationRepository notificationRepository;

    @Test
    public void save_wellcom_noti(){
        NotificationDto notificationDto = NotificationDto.builder()
                .memberId(30L)
                .notiMessage("라이틴 가입을 축하드립니다")
                .notiRead(false)
                .notiType(NotiType.WELLCOM).build();
        notificationRepository.save(notificationDto.toEntity());
    }

    @Test
    public void save_article_noti(){
        NotificationDto notificationDto = NotificationDto.builder()
                .memberId(30L)
                .notiMessage("에서 좋아요를 받았어요")
                .notiRead(false)
                .notiType(NotiType.ARTICLE_LIKE)
                .fromId(1L)
                .build();
        notificationRepository.save(notificationDto.toEntity());
    }
}
