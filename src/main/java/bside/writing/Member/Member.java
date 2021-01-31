package bside.writing.Member;


import java.util.List;

public class Member {
    private Long userNumber;
    private String name;
    private String EmailAddress;
    private List<Long> ArticleNumberList;

    public Member(Long userNumber, String name, String emailAddress, List<Long> articleNumberList) {
        this.userNumber = userNumber;
        this.name = name;
        EmailAddress = emailAddress;
        ArticleNumberList = articleNumberList;
    }

    public Long getUserNumber() {
        return userNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUserNumber(Long userNumber) {
        this.userNumber = userNumber;
    }

    public String getEmailAddress() {
        return EmailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        EmailAddress = emailAddress;
    }

    public List<Long> getArticleNumberList() {
        return ArticleNumberList;
    }

    public void setArticleNumberList(List<Long> articleNumberList) {
        ArticleNumberList = articleNumberList;
    }

    @Override
    public String toString() {
        return "Member{" +
                "userNumber=" + userNumber +
                ", nickName='" + name + '\'' +
                ", EmailAddress='" + EmailAddress + '\'' +
                ", ArticleNumberList=" + ArticleNumberList +
                '}';
    }
    

}
