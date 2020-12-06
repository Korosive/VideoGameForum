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

    @Column(name = "username")
    private String username;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "date_created")
    private Date date_created;

    @Column(name = "last_updated")
    private Date last_updated;

    public Post(UUID post_id, String username, String title, String description, Date date_created, Date last_updated) {
        this.post_id = post_id;
        this.username = username;
        this.title = title;
        this.description = description;
        this.date_created = date_created;
        this.last_updated = last_updated;
    }

    public UUID getPost_id() {
        return post_id;
    }

    public String getUsername() {
        return username;
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

    public Date getLast_updated() {
        return last_updated;
    }
}
