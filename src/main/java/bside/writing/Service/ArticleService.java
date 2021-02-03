package bside.writing.Service;

import bside.writing.Article.*;
import bside.writing.Repository.ArticleRepository;
import bside.writing.Repository.MemoryArticleRepository;

import java.awt.geom.AffineTransform;
import java.util.List;
import java.util.Optional;

public class ArticleService {
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
