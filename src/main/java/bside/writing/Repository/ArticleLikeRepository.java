package bside.writing.Repository;

import bside.writing.domain.article.Article;
import bside.writing.domain.article.ArticleLike;
import bside.writing.domain.article.ArticleSubmitCount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

public interface ArticleLikeRepository extends JpaRepository<ArticleLike, Long> {

    @Query(value = "SELECT * FROM article_like a " +
            "where a.article_id = ?1 AND a.member_id = ?2", nativeQuery = true)
    Optional<ArticleLike> findDuplicate(Long articleId , Long uid);


}
