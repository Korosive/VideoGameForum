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

    @Column(name = "content")
    private String content;

    @Column(name = "date_created")
    private Date date_created;

    @Column(name = "enabled")
    private boolean enabled;

    public Article(UUID article_id, String username, String title, String content, Date date_created, boolean enabled) {
        this.article_id = article_id;
        this.username = username;
        this.title = title;
        this.content = content;
        this.date_created = date_created;
        this.enabled = enabled;
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

    public String getContent() {
        return content;
    }

    public Date getDate_created() {
        return date_created;
    }

    public boolean isEnabled() {
        return enabled;
    }
}
