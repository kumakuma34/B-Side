package bside.writing.ArticleTest;

import bside.writing.Repository.ChallengeRepository;
import bside.writing.Service.ArticleService;
import bside.writing.Service.BadgeService;
import bside.writing.dto.ArticleDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
public class ArticleTest {
    @Autowired
    public ArticleService articleService;
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
                .visible("1")
                .build();
        //when
        System.out.println(articleService.addNewArticle(request,47L));

    }
}
