package com.example.demo.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name="article")
public class Article {
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public boolean isFavorited() {
        return favorited;
    }

    public void setFavorited(boolean favorited) {
        this.favorited = favorited;
    }

    public int getFavoritesCount() {
        return favoritesCount;
    }

    public void setFavoritesCount(int favoritesCount) {
        this.favoritesCount = favoritesCount;
    }



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String[] getTags() {
        return tags;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }



    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @Id
    @JsonProperty(value ="id",required = false)
    private long id;

    @NaturalId
    @Column(nullable = false, unique = true)
    @JsonProperty(value ="uuid",required = false)
    private String uuid;


    @JsonProperty(value ="slug",required = false)
    private String slug;

    @JsonProperty(value ="title",required = false)
    private String title;

    @JsonProperty(value ="description",required = false)
    private String description;
    @JsonProperty(value ="body",required = false)
    private String body;

    @Column(name="tags")
    @JsonProperty(value ="tags",required = false)
    private String[] tags;

    @JsonProperty(value ="createdAt",required = false)
    @CreatedDate
    private Date createdAt;

    @JsonProperty(value ="updatedAt",required = false)
    @CreatedDate
    @UpdateTimestamp
    private Date updatedAt;

    @JsonProperty(value ="favorited",required = false)
    private boolean favorited;

    @JsonProperty(value ="favoritesCount",required = false)
    private int favoritesCount;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Article article = (Article) o;
        return id == article.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
