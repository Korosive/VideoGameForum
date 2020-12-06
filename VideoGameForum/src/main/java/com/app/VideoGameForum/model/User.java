package com.app.VideoGameForum.model;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID user_id;

    @Column(name = "email")
    private String email;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "date_created")
    private Date date_created;

    @Column(name = "last_updated")
    private Date last_updated;

    @Column(name = "enabled")
    private boolean enabled;

    public User(UUID user_id, String email, String username, String password, Date date_created, Date last_updated, boolean enabled) {
        this.user_id = user_id;
        this.email = email;
        this.username = username;
        this.password = password;
        this.date_created = date_created;
        this.last_updated = last_updated;
        this.enabled = enabled;
    }

    public UUID getUser_id() {
        return user_id;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
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
