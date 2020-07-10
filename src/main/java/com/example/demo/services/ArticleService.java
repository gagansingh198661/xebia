package com.example.demo.services;

import com.example.demo.Utility;
import com.example.demo.dtos.Article;
import com.example.demo.repositories.ArticleRepository;
import net.ricecode.similarity.JaroWinklerStrategy;
import net.ricecode.similarity.SimilarityStrategy;
import net.ricecode.similarity.StringSimilarityService;
import net.ricecode.similarity.StringSimilarityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ArticleService {

    @Autowired
    ArticleRepository repository;

    public List<Article> getAllArticles(){
        return repository.findAll();
    }

    public Article getArticleByUuid(String uuid){
        return repository.findByUuid(uuid).get(0);
    }

    public boolean deleteArticleByUuid(String uuid){
        boolean noError=true;
        try{
            repository.deleteFromArticleUuid(uuid);
        }catch(Exception e){
            noError=false;
        }
        return noError;
    }

    public boolean createArticle(Article article){
        boolean noError=true;
        try {

            List<Article> articleList=repository.findAll();
            for(Article articleObj:articleList){
                if(isSimilar(article,articleObj)){
                    return false;
                }
            }

            article.setUuid(article.hashCode() + "");
            String[] tags=article.getTags();
            String[] newTags=new String[tags.length];
            for(int i=0;i<tags.length;i++){
                newTags[i]=tags[i].toLowerCase();
            }
            article.setTags(newTags);
            repository.save(article);
        }catch(Exception e){
            System.out.println(e);
            noError=false;
        }
        return noError;
    }

    private boolean isSimilar(Article article, Article articleObj) {
        String target = articleObj.getBody();
        String source = article.getBody();
        SimilarityStrategy strategy = new JaroWinklerStrategy();
        StringSimilarityService service = new StringSimilarityServiceImpl(strategy);
        double score = service.score(source, target);
        if(score>.7){
            return true;
        }
        return false;
    }

    public Article updateArticle(Article article,String uuid){
        Article article1=null;
        try {
            article1 = repository.findByUuid(uuid).get(0);
            Utility.copyArticles(article1, article);
            article1=repository.save(article1);
        }catch(Exception e){
            System.out.println(e);

        }
        return article1;
    }
}
