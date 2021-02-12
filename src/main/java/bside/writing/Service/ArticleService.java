package bside.writing.Service;

import bside.writing.Article.*;
import bside.writing.Repository.ArticleRepository;
import bside.writing.Repository.MemoryArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.geom.AffineTransform;
import java.util.List;
import java.util.Optional;

//@Service
public class ArticleService {
    // @Autowired
    private final ArticleRepository articleRepository = new MemoryArticleRepository();

    public Article saveArticle(Article saveArticle){
        return articleRepository.save(saveArticle);
    }

    public Optional<Article> deleteArticle(Long deleteArticleNumber){
        return articleRepository.delete(deleteArticleNumber);
    }

    public List<Article> searchByTitle(String searchTitle){
        return articleRepository.searchByTitle(searchTitle);
    }

    public List<Article> loadArticle(Long memberId){
        return articleRepository.searchByRegisterId(memberId);
    }

}
