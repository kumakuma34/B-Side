package bside.writing.Repository;
import bside.writing.Article.*;

import java.util.List;
import java.util.Optional;

public interface ArticleRepository {
    Article save(Article article);
    Optional<Article> delete(Long articleNumber);
    //List<Article> findByUserNumber(Long userId); >>>????????
    List<Article> searchByTitle(String searchTitle);
    List<Article> searchByRegisterId(Long searchId);
}
