package bside.writing.controller;

import bside.writing.Service.*;
import bside.writing.dto.ArticleDto;
import bside.writing.dto.ChallengeDto;
import bside.writing.enums.ChallengeStatusCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class ArticleController {
    public final TokenService tokenService;
    public final ArticleService articleService;
    public final ArticleLikeService articleLikeService;

    //Article 신규 생성
    @CrossOrigin("*")
    @RequestMapping(value = "article", method = RequestMethod.POST)
    public Map<String, Object> saveArticle(@RequestBody ArticleDto.Request request, @RequestHeader(name="Authorization") String accessToken ) throws IOException{
        Long uid = tokenService.getUid(accessToken);
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("article_id", articleService.addNewArticle(request, uid));
        return response;
    }

    //글 조회
    @CrossOrigin("*")
    @RequestMapping(value = "article", method = RequestMethod.GET)
    public Map<String, Object> getArticle(@RequestHeader(name="Authorization") String accessToken ) throws IOException{
        Long uid = tokenService.getUid(accessToken);
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("temp", articleService.getTempArticle(uid));
        response.put("submit", articleService.getSubmitArticle(uid));
        return response;
    }

    //저장한글 조회
    @CrossOrigin("*")
    @RequestMapping(value = "article/temp/{article_id}", method = RequestMethod.GET)
    public ArticleDto.TempArticleResponse getTempArticleDetail(@PathVariable String article_id, @RequestHeader(name="Authorization") String accessToken ) throws IOException{
        Long articleId = Long.valueOf(article_id);
        return articleService.getTempArticleDetail(articleId);
    }

    //제출한글 조회
    @CrossOrigin("*")
    @RequestMapping(value = "article/{article_id}", method = RequestMethod.GET)
    public ArticleDto.SubmitArticleResponse getArticleDetail(@PathVariable String article_id, @RequestHeader(name="Authorization") String accessToken ) throws IOException{
        Long articleId = Long.valueOf(article_id);
        return articleService.getSubmitArticleDetail(articleId);
    }

    //article 삭제
    @CrossOrigin("*")
    @RequestMapping(value = "article/{article_id}", method = RequestMethod.DELETE)
    public String deleteArticle(@PathVariable String article_id , @RequestHeader(name="Authorization") String accessToken) throws IOException{
        Long uid  = tokenService.getUid(accessToken);
        articleService.deleteArticle(Long.valueOf(article_id));
        return article_id + " deleted";
    }

    //글 좋아요
    @CrossOrigin("*")
    @RequestMapping(value = "article/like/{article_id}", method = RequestMethod.POST)
    public String likeArticle(@PathVariable String article_id , @RequestHeader(name="Authorization") String accessToken) throws IOException{
        Long uid  = tokenService.getUid(accessToken);
        articleLikeService.articleLike(Long.valueOf(article_id), uid);
        return article_id + " like";
    }

}
