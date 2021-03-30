package bside.writing.notification;

import bside.writing.Repository.NotificationRepository;
import bside.writing.domain.notification.Notification;
import bside.writing.dto.NotificationDto;
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
@Transactional
public class noti_Repo_test {

    @Autowired
    NotificationRepository notificationRepository;

    @Test
    public void 알람_생성(){
        NotificationDto notificationDto = NotificationDto.builder()
                .memberId(30L)
                .fromMemberId(47L)
                .notiMessage("장현수 지금 죽이고 놀러가겠습니다")
                .notiRead(false)
                .notiType("유저알람")
                .notiUrl("www.google.com")
                .build();
        Notification save = notificationRepository.save(notificationDto.toEntity());
        Assertions.assertThat(save.getMemberId()).isEqualTo(notificationDto.getMemberId());
    }

    @Test
    public void 알람_조회(){
        Optional<List<Notification>> byMemberId = notificationRepository.findByMemberId(30L);

    }

}
