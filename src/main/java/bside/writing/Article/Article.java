package bside.writing.Article;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Article {
    private Long userNumber;
    private Long articleNumber;
    private String title;
    private String content;
    private Date registerDate;
    private Date modifyDate;

    public Article(Long userNumber, Long articleNumber, String title, String content, Date registerDate, Date modifyDate) {
        this.userNumber = userNumber;
        this.articleNumber = articleNumber;
        this.title = title;
        this.content = content;
        this.registerDate = registerDate;
        this.modifyDate = modifyDate;
    }

    public Long getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(Long userNumber) {
        this.userNumber = userNumber;
    }

    public Long getArticleNumber() {
        return articleNumber;
    }

    public void setArticleNumber(Long articleNumber) {
        this.articleNumber = articleNumber;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    @Override
    public String toString() {
        return "Aritcle{" +
                "userNumber=" + userNumber +
                ", articleNumber=" + articleNumber +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", registerDate=" + registerDate +
                ", modifyDate=" + modifyDate +
                '}';
    }
}
