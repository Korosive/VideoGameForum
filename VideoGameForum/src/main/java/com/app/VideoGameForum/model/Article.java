package com.app.VideoGameForum.model;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "articles")
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID article_id;

    @Column(name = "username")
    private String username;

    @Column(name = "title")
    private String title;

    @Column(name = "date_created")
    private Date date_created;

    public Article(UUID article_id, String username, String title, Date date_created) {
        this.article_id = article_id;
        this.username = username;
        this.title = title;
        this.date_created = date_created;
    }

    public UUID getArticle_id() {
        return article_id;
    }

    public String getUsername() {
        return username;
    }

    public String getTitle() {
        return title;
    }

    public Date getDate_created() {
        return date_created;
    }
}
