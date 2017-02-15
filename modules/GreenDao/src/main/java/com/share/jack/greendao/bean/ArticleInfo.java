package com.share.jack.greendao.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

/**
 *
 */
@Entity
public class ArticleInfo {

    private Long id;

    private String articleTitle;

    private String articleContent;

    @Generated(hash = 1761964833)
    public ArticleInfo(Long id, String articleTitle, String articleContent) {
        this.id = id;
        this.articleTitle = articleTitle;
        this.articleContent = articleContent;
    }

    @Generated(hash = 895234147)
    public ArticleInfo() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public String getArticleContent() {
        return articleContent;
    }

    public void setArticleContent(String articleContent) {
        this.articleContent = articleContent;
    }
}