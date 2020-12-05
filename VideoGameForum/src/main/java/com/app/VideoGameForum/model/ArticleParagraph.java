package com.app.VideoGameForum.model;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "article_paragraphs")
public class ArticleParagraph {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID paragraph_id;

    @Column(name = "article_id")
    private UUID article_id;

    @Column(name = "header")
    private String header;

    @Column(name = "paragraph")
    private String paragraph;

    public ArticleParagraph(UUID paragraph_id, UUID article_id, String header, String paragraph) {
        this.paragraph_id = paragraph_id;
        this.article_id = article_id;
        this.header = header;
        this.paragraph = paragraph;
    }

    public UUID getParagraph_id() {
        return paragraph_id;
    }

    public UUID getArticle_id() {
        return article_id;
    }

    public String getHeader() {
        return header;
    }

    public String getParagraph() {
        return paragraph;
    }
}
