package com.example.demo.repositories;

import com.example.demo.dtos.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article,String> {
    List<Article> findByUuid(String uuid);

    @Modifying
    @Query("delete from Article u where u.uuid = ?1")
    public void deleteFromArticleUuid(String uuid);

}
