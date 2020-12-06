package com.app.VideoGameForum.util;

import com.app.VideoGameForum.model.Article;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class ArticleMapper implements RowMapper<Article> {
    @Override
    public Article mapRow(ResultSet resultSet, int i) throws SQLException {
        return new Article(
                resultSet.getObject("article_id", UUID.class),
                resultSet.getString("username"),
                resultSet.getString("title"),
                resultSet.getString("content"),
                resultSet.getDate("date_created"),
                resultSet.getDate("last_updated"),
                resultSet.getBoolean("enabled")
        );
    }
}
