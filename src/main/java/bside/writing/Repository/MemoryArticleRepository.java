package bside.writing.Repository;

import bside.writing.Article.Article;
import bside.writing.Member.Member;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class MemoryArticleRepository implements ArticleRepository{

    private ConcurrentHashMap<Long, Article> store = new ConcurrentHashMap<>();
    private static long sequence = 0L;

    @Override
    public Article save(Article article) {
        article.setArticleNumber(++sequence);
        store.put(article.getArticleNumber(), article);
        return article;
    }

    //Optional로 해도되나..? null이면 삭제 처리 안됬다고 처리하는게 나을것같긴한데...
    // > 아 아니네 null 결과값으로 던져주면 그때 처리하면되겠네 멍청이

    @Override
    public Optional<Article> delete(Long articleNumber) {
        Optional<Article> deletedArticle = Optional.ofNullable(store.get(articleNumber));
        store.remove(articleNumber);
        return deletedArticle;
    }

    @Override
    public List<Article> searchByTitle(String searchTitle) {
        List<Article> resultList = null;
        Collection<Article> allValues = store.values();
        Iterator<Article> iter = allValues.iterator();

        while(iter.hasNext()){
            Article now = iter.next();
            if(now.getTitle().contains(searchTitle)){
                resultList.add(now);
            }
        }
        return resultList;
    }

    @Override
    public List<Article> searchByRegisterId(Long searchId) {
        List<Article> resultList = null;
        Collection<Article> allValues = store.values();
        Iterator<Article> iter = allValues.iterator();

        while(iter.hasNext()){
            Article now = iter.next();
            if(now.getUserNumber() == searchId){
                resultList.add(now);
            }
        }

        return resultList;
    }
}
