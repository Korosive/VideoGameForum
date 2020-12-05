package com.app.VideoGameForum.util;

import com.app.VideoGameForum.model.ArticleParagraph;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class ArticleParagraphMapper implements RowMapper<ArticleParagraph> {
    @Override
    public ArticleParagraph mapRow(ResultSet resultSet, int i) throws SQLException {
        return new ArticleParagraph(
                resultSet.getObject("paragraph_id", UUID.class),
                resultSet.getObject("article_id", UUID.class),
                resultSet.getString("header"),
                resultSet.getString("paragraph")
        );
    }
}
