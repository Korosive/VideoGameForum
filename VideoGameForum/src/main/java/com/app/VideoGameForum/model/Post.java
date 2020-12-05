package com.app.VideoGameForum.model;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID post_id;

    @Column(name = "user_id")
    private UUID user_id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "date_created")
    private Date date_created;

    public Post(UUID post_id, UUID user_id, String title, String description, Date date_created) {
        this.post_id = post_id;
        this.user_id = user_id;
        this.title = title;
        this.description = description;
        this.date_created = date_created;
    }

    public UUID getPost_id() {
        return post_id;
    }

    public UUID getUser_id() {
        return user_id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Date getDate_created() {
        return date_created;
    }
}
