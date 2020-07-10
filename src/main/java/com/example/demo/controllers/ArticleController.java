package com.example.demo.controllers;


import com.example.demo.Utility;
import com.example.demo.dtos.Article;
import com.example.demo.dtos.HumanWordSpeed;
import com.example.demo.dtos.TagDTO;
import com.example.demo.dtos.TimeToReadDTO;
import com.example.demo.services.ArticleService;
import com.example.demo.services.HumanWordSpeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RestController
@RequestMapping("/api")
public class ArticleController {

    @Autowired
    ArticleService articleService;

    @Autowired
    HumanWordSpeedService speedService;


    @GetMapping("/articles")
    public ResponseEntity<List<Article>> getAllarticles(){
        List<Article> artList=new LinkedList<>();
        try {
            artList=articleService.getAllArticles();
        }catch(Exception e){
            return new ResponseEntity("", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(artList,HttpStatus.OK);
    }

    @GetMapping("/articles/{slug_uuid}")
    public ResponseEntity<Article> getArticleWithId(@PathVariable String slug_uuid){
        Article articleTobeSent=null;
        try{
            articleTobeSent= articleService.getArticleByUuid(slug_uuid);

        }catch(Exception e){
            return new ResponseEntity("No Record Found",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(articleTobeSent,HttpStatus.OK);
    }

    @DeleteMapping("/article/{slug_uuid}")
    public ResponseEntity<Article> deleteArticleWithId(@PathVariable String slug_uuid) {
        try{
            boolean isNoError=articleService.deleteArticleByUuid(slug_uuid);
            if(isNoError){
                return new ResponseEntity("Record Deleted",HttpStatus.OK);
            }

        }catch(Exception e){
            return new ResponseEntity("Something Went Wrong",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity("Something Went Wrong",HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("/articles")
    public ResponseEntity<String> createArticle(@RequestBody Article article) {
        boolean noError=false;
        try {
            if(Utility.validateArticle(article)) {
                noError = articleService.createArticle(article);
            }else{
                return new ResponseEntity("Something Went Wrong", HttpStatus.BAD_REQUEST);
            }
            if (noError) {
                return new ResponseEntity("Record Created", HttpStatus.CREATED);
            }else{
                return new ResponseEntity("Something Went Wrong", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity("Something Went Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping ("/articles/{slug_uuid}")
    public ResponseEntity<Article> updateArticleWithId(@PathVariable String slug_uuid,@RequestBody Article article) {
        boolean noError=false;
        try {
            Article article1 = articleService.updateArticle(article, slug_uuid);
            return new ResponseEntity(article1, HttpStatus.OK);

        }catch(Exception e){
            return new ResponseEntity("Something Went Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/articles/{slug_uuid}/timetoread")
    public ResponseEntity<TimeToReadDTO> gettime(@PathVariable String slug_uuid){
        try{
            Article article=articleService.getArticleByUuid(slug_uuid);
            String body=article.getBody();
            int length=body.length();
            HumanWordSpeed hws=speedService.get();
            int speed=hws.getWordNumberMinute();
            int secs= length%speed;
            int mins= length/speed;
            TimeToReadDTO tt=new TimeToReadDTO();
            tt.setArticleId(article.getUuid());
            tt.setMins(mins+"");
            tt.setSecs(secs+"");
            return new ResponseEntity<>(tt,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity("Something Went Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/articles/tags")
    public ResponseEntity<TagDTO> getTags(){
        List<Article> articles= articleService.getAllArticles();
        Map<String,Integer> map=new HashMap<>();
        for(Article article:articles){
            String[] tags=article.getTags();
            for(String tag:tags){
                if(map.containsKey(tag)){
                    int freq=map.get(tag);
                    freq++;
                    map.put(tag,freq);
                }else{
                    map.put(tag,1);
                }
            }
        }
        List<TagDTO> tagDTOList=new LinkedList<>();
        for(Map.Entry<String,Integer> entry:map.entrySet()){
            tagDTOList.add(new TagDTO(entry.getKey(),entry.getValue()));
        }
        return new ResponseEntity(tagDTOList,HttpStatus.OK);
    }


}


