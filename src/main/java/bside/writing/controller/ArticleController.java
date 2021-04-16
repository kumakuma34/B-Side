package bside.writing.controller;

import bside.writing.Service.ArticleService;
import bside.writing.Service.ChallengeMemberService;
import bside.writing.Service.ChallengeService;
import bside.writing.Service.TokenService;
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
    @RequestMapping(value = "article", method = RequestMethod.GET)
    public Map<String, Object> getArticle(@RequestHeader(name="Authorization") String accessToken ) throws IOException{
        Long uid = tokenService.getUid(accessToken);
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("temp", articleService.getTempArticle(uid));
        response.put("submit", articleService.getSubmitArticle(uid));
        return response;
    }

}
