package bside.writing.ArticleTest;

import bside.writing.Repository.ArticleRepository;
import bside.writing.Repository.ChallengeRepository;
import bside.writing.Service.ArticleService;
import bside.writing.Service.BadgeService;
import bside.writing.dto.ArticleDto;
import org.apache.tomcat.jni.Local;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;

@SpringBootTest
public class ArticleTest {
    @Autowired
    public ArticleService articleService;
    @Autowired
    public ArticleRepository articleRepository;
    @Test
    void 주차조회함수(){
        //Given
        Long challengeId = 30L;
        //when
        int weeks = articleService.getWeekCnt(challengeId);
        //then
        System.out.println(weeks);
    }

    @Test
    void 주차제출횟수조회(){
        //Given
        Long challengeId = 32L;
        Long memberId = 47L;
        int week = 1;
        //when
        int submitCnt = articleService.getSubmitCnt(week , challengeId,memberId);
        //then
        System.out.println(submitCnt);
    }
    @Test
    void 글생성(){
        //Given
        String text = "Find this better, because I can directly choose which Text-Type the column will have in database.\n" +
                "\n" +
                "For columnDefinition it is also good to read this.\n" +
                "\n" +
                "EDIT: Please pay attention to Adam Siemions comment and check the database engine you are using, before applying columnDefinition = \"TEXT\".";
        ArticleDto.Request request = ArticleDto.Request.builder()
                .articleTitle("긴내용테스")
                .articleDetail(text)
                .challengeId(32L)
                .status("0")
                .build();
        //when
        System.out.println(articleService.addNewArticle(request,47L));

    }

    @Test
    public void 달성도조회(){
        //given
        Long challengeId = 11L;
        Long uid = 47L;
        System.out.println(articleRepository.findSubmitTime(challengeId ,1,1,uid));
        Map<Integer , Object> result = articleService.getSubmitStatus(uid,challengeId);
        System.out.println(result);
    }
}
