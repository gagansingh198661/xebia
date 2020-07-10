package com.example.demo;

import com.example.demo.dtos.Article;

public class Utility {
    public  static boolean validateArticle(Article article){
        if(article.getTags().length==0&&article.getBody().isEmpty()&&article.getTitle().isEmpty()
            &&article.getDescription().isEmpty()&&article.getSlug().isEmpty()){
            return false;
        }
        return true;
    }

    public static void copyArticles(Article original,Article modified){
        original.setTitle(modified.getTitle());
        original.setTags(modified.getTags());

        original.setBody(modified.getBody());
        original.setFavorited(modified.isFavorited());
        original.setDescription(modified.getDescription());
        original.setFavoritesCount(modified.getFavoritesCount());
        original.setSlug(modified.getSlug());

    }
}
