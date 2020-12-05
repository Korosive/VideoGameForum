package com.app.VideoGameForum.util;

import com.app.VideoGameForum.model.Post;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class PostMapper implements RowMapper<Post> {
    @Override
    public Post mapRow(ResultSet resultSet, int i) throws SQLException {
        return new Post(
                resultSet.getObject("post_id", UUID.class),
                resultSet.getObject("user_id", UUID.class),
                resultSet.getString("title"),
                resultSet.getString("description"),
                resultSet.getDate("date_created")
        );
    }
}
