package bside.writing.Service;

import bside.writing.Repository.ArticleRepository;
import bside.writing.Repository.ChallengeRepository;
import bside.writing.domain.article.Article;
import bside.writing.dto.ArticleDto;
import bside.writing.enums.ArticleStatusCode;
import javassist.compiler.ast.Pair;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Service
@RequiredArgsConstructor
public class ArticleService {
    public final ChallengeRepository challengeRepository;
    public final ArticleRepository articleRepository;

    //주차 계산 함수
    public int getWeekCnt(Long challenge_id){
        LocalDate startDt = challengeRepository.findById(challenge_id).get().getStartDt();
        LocalDate now = LocalDate.now();
        long weeks = ChronoUnit.WEEKS.between(startDt, now);
        return Long.valueOf(weeks).intValue() + 1;
    }

    //해당 주차 제출 횟수 계산 함수
    public int getSubmitCnt(int week, Long challenge_id , Long uid){
        if(articleRepository.findMaxSubmitCnt(challenge_id,uid,week).isPresent())
            return articleRepository.findMaxSubmitCnt(challenge_id,uid,week).get() + 1;//여기문법왜이래...
        else
            return 1;
    }
    //Article 생성 함수
    public Long addNewArticle(ArticleDto.Request request , Long uid){
        int week = getWeekCnt(request.getChallengeId());
        int submitCnt = getSubmitCnt(week,request.getChallengeId(),uid);
        if(request.getStatus() == 2) {
            week = 0; submitCnt = 0;
        }
        Article entity = Article.builder()
                .articleTitle(request.getArticleTitle())
                .articleDetail(request.getArticleDetail())
                .createdId(uid)
                .challengeId(request.getChallengeId())
                .week(week)
                .submitCnt(submitCnt)
                .status(request.getStatus())
                .build();
        return articleRepository.save(entity).getArticleId();
    }

    public ArticleDto.ResponseAsList makeResponseAsList(Article entity){
       challengeRepository.findById(entity.getChallengeId()).orElseThrow(()->new IllegalArgumentException("no such Challenge"));
       String challengeTitle = challengeRepository.findById(entity.getChallengeId()).get().getChallengeTitle();
       return ArticleDto.ResponseAsList.builder()
               .articleId(entity.getArticleId())
               .articleTitle(entity.getArticleTitle())
               .createdDate(entity.getCreatedDate())
               .status(ArticleStatusCode.fromStatus(entity.getStatus()).name())
               .challengeTitle(challengeTitle)
               .week(entity.getWeek())
               .submitCnt(entity.getSubmitCnt())
               .build();
    }
    //저장한 글 조회
    public List<ArticleDto.ResponseAsList> getTempArticle(Long uid){
        List<ArticleDto.ResponseAsList> result = new ArrayList<>();
        if(articleRepository.findTempArticle(uid).isPresent()){
            articleRepository.findTempArticle(uid).get().forEach(article ->
                    result.add(ArticleDto.ResponseAsList.builder()
                            .articleId(article.getArticleId())
                            .articleTitle(article.getArticleTitle())
                            .createdDate(article.getCreatedDate())
                            .status(ArticleStatusCode.fromStatus(article.getStatus()).name())
                            .build()));
        }
        return result;
    }

    //제출한 글 조회
    public List<ArticleDto.ResponseAsList> getSubmitArticle(Long uid){
        List<ArticleDto.ResponseAsList> result = new ArrayList<>();
        if(articleRepository.findSubmitArticle(uid).isPresent()){
            articleRepository.findSubmitArticle(uid).get().forEach(article ->
                    result.add(makeResponseAsList(article)));
        }
        return result;
    }
    //제출 현황 조회 함수
    public Map<Integer , Object> getSubmitStatus(Long uid, Long challenge_id){
        Map<Integer,Object> result = new LinkedHashMap<>();

        int week = getWeekCnt(challenge_id);
        int submitCnt = challengeRepository.findById(challenge_id).get().getSubmitDaysCnt();
        for(int i = 1 ;i <= week ; i++){
            List<Object> list = new ArrayList<>();
            for(int j = 1; j <= submitCnt; j++){
                if(articleRepository.findSubmitTime(challenge_id,i,j,uid).isPresent())
                    list.add(articleRepository.findSubmitTime(challenge_id,i,j,uid).get());
                else{
                    if(i == week){
                        list.add("CURRENT");
                        break;
                    }
                    else{
                        list.add("FALSE");
                    }
                }
            }
            result.put(i,list);
        }
        return result;
    }
}