package bside.writing.Repository;

import bside.writing.domain.article.Article;
import bside.writing.domain.challenge.Challenge;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    @Query(value = "SELECT MAX(a.submit_cnt) FROM article a " +
            "where a.challenge_id = ?1 AND a.created_id = ?2 and a.week = ?3 and a.status != 2" , nativeQuery = true)
    Optional<Integer> findMaxSubmitCnt(Long challenge_id , Long member_id , int week);
}
