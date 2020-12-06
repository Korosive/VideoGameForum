package com.app.VideoGameForum.util;

import com.app.VideoGameForum.model.Comment;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class CommentMapper implements RowMapper<Comment> {
    @Override
    public Comment mapRow(ResultSet resultSet, int i) throws SQLException {
        return new Comment(
                resultSet.getObject("comment_id", UUID.class),
                resultSet.getObject("post_id", UUID.class),
                resultSet.getString("username"),
                resultSet.getString("comment"),
                resultSet.getDate("date_created")
        );
    }
}
