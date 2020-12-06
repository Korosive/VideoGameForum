package com.app.VideoGameForum.model;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID comment_id;

    @Column(name = "post_id")
    private UUID post_id;

    @Column(name = "username")
    private String username;

    @Column(name = "comment")
    private String comment;

    @Column(name = "date_created")
    private Date date_created;

    @Column(name = "last_updated")
    private Date last_updated;

    @Column(name = "enabled")
    private boolean enabled;

    public Comment(UUID comment_id, UUID post_id, String username, String comment, Date date_created, Date last_updated, boolean enabled) {
        this.comment_id = comment_id;
        this.post_id = post_id;
        this.username = username;
        this.comment = comment;
        this.date_created = date_created;
        this.last_updated = last_updated;
        this.enabled = enabled;
    }

    public UUID getComment_id() {
        return comment_id;
    }

    public UUID getPost_id() {
        return post_id;
    }

    public String getUsername() {
        return username;
    }

    public String getComment() {
        return comment;
    }

    public Date getDate_created() {
        return date_created;
    }

    public Date getLast_updated() {
        return last_updated;
    }

    public boolean isEnabled() {
        return enabled;
    }
}
