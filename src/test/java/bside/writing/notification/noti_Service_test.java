package bside.writing.notification;

import bside.writing.Service.NotificationService;
import bside.writing.dto.NotificationDto;
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

    @Test
    public void 노티_맴버아이디로_조회_예외조건_알람이_없는_경우(){
        List<NotificationDto> memberNotification = notificationService.getNotification(-1L);
        Assertions.assertThat(memberNotification).isEqualTo(null);
    }

    @Test
    public void 노티_저장(){

        //given
        NotificationDto.Challenge testDto = NotificationDto.Challenge.builder()
                .memberId(1L)
                .fromChallengeId(1L)
                .notiType("챌린지알람")
                .notiMessage("장현수 챌린지 : 죽이고")
                .notiUrl("www.Kill_Kuma_Kuma.com")
                .notiRead(false)
                .build();

        NotificationDto testDto2 = NotificationDto.builder()
                .memberId(2L)
                .fromArticleId(1L)
                .notiType("생성")
                .notiMessage("장현수징징이")
                .notiRead(true)
                .build();

        //when
        NotificationDto resDto = notificationService.save(testDto);
        NotificationDto resDto2 = notificationService.save(testDto2);

        //then
        Assertions.assertThat(testDto.getMemberId()).isEqualTo(resDto.getMemberId());
        Assertions.assertThat(testDto.getNotiMessage()).isEqualTo(resDto.getNotiMessage());

        Assertions.assertThat(testDto2.getMemberId()).isEqualTo(resDto2.getMemberId());
        Assertions.assertThat(testDto2.getNotiMessage()).isEqualTo(resDto2.getNotiMessage());

    }

}
