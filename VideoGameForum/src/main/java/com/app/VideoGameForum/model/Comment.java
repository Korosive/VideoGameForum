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

    @Column(name = "user_id")
    private UUID user_id;

    @Column(name = "comment")
    private String comment;

    @Column(name = "date_created")
    private Date date_created;

    public Comment(UUID comment_id, UUID post_id, UUID user_id, String comment, Date date_created) {
        this.comment_id = comment_id;
        this.post_id = post_id;
        this.user_id = user_id;
        this.comment = comment;
        this.date_created = date_created;
    }

    public UUID getComment_id() {
        return comment_id;
    }

    public UUID getPost_id() {
        return post_id;
    }

    public UUID getUser_id() {
        return user_id;
    }

    public String getComment() {
        return comment;
    }

    public Date getDate_created() {
        return date_created;
    }
}
