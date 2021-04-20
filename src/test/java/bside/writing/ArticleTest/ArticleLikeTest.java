package bside.writing.ArticleTest;

import bside.writing.Repository.ArticleRepository;
import bside.writing.Service.ArticleLikeService;
import bside.writing.Service.ArticleService;
import bside.writing.domain.article.ArticleSubmitCount;
import bside.writing.dto.ArticleDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

@SpringBootTest
public class ArticleLikeTest {
    @Autowired
    public ArticleLikeService articleLikeService;

    @Test
    void 중복검(){
        //Give
        Long uid = 47L;
        Long articleId = 4L;

        //articleLikeService.articleLike(articleId,uid);
    }

}
