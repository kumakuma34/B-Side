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

}
