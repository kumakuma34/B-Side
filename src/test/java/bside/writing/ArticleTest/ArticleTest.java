package bside.writing.ArticleTest;

import bside.writing.Repository.ArticleRepository;
import bside.writing.Service.ArticleService;
import bside.writing.domain.article.ArticleSubmitCount;
import bside.writing.dto.ArticleDto;
import bside.writing.domain.article.RankResult;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
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
                .articleTitle("임시글임")
                .articleDetail(text)
                .challengeId(32L)
                .status(2)
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

    @Test
    public void 글제출횟수조회(){
        //given
        Long challengeId = 40L;

        List<ArticleSubmitCount> result = articleRepository.findSubmitCount(challengeId);
        result.forEach(e->System.out.println(e.getMemberId() + " : " + e.getSubmitCnt()));

        List<RankResult<String , Integer>> result2 = articleService.getRank(challengeId);
        result2.forEach(e->System.out.println(e.getFirst()));
    }
}
