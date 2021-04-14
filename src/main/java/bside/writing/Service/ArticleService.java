package bside.writing.Service;

import bside.writing.Repository.ArticleRepository;
import bside.writing.Repository.ChallengeRepository;
import bside.writing.domain.article.Article;
import bside.writing.dto.ArticleDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

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
}
