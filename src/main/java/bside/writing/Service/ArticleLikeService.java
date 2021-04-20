package bside.writing.Service;

import bside.writing.Repository.ArticleLikeRepository;
import bside.writing.Repository.ArticleRepository;
import bside.writing.Repository.ChallengeRepository;
import bside.writing.domain.article.Article;
import bside.writing.domain.article.ArticleLike;
import bside.writing.domain.article.ArticleSubmitCount;
import bside.writing.domain.article.RankResult;
import bside.writing.domain.challenge.Challenge;
import bside.writing.dto.ArticleDto;
import bside.writing.dto.BadgeSaveDto;
import bside.writing.enums.ArticleStatusCode;
import bside.writing.enums.BadgeCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Service
@RequiredArgsConstructor
public class ArticleLikeService {

    public final ArticleLikeRepository articleLikeRepository;

    public void articleLike(Long articleId , Long uid){
        Optional<ArticleLike> result = articleLikeRepository.findDuplicate(articleId,uid);
        if(result.isPresent())
            throw new IllegalArgumentException("Already Liked that article");
        ArticleLike entity = ArticleLike.builder()
                .articleId(articleId)
                .memberId(uid)
                .build();

        articleLikeRepository.save(entity);
    }
}
