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

    @Column(name = "user_id")
    private UUID user_id;

    @Column(name = "title")
    private String title;

    @Column(name = "date_created")
    private Date date_created;

    public Article(UUID article_id, UUID user_id, String title, Date date_created) {
        this.article_id = article_id;
        this.user_id = user_id;
        this.title = title;
        this.date_created = date_created;
    }

    public UUID getArticle_id() {
        return article_id;
    }

    public UUID getUser_id() {
        return user_id;
    }

    public String getTitle() {
        return title;
    }

    public Date getDate_created() {
        return date_created;
    }
}
