package bside.writing.Member;


import java.util.List;

public class Member {
    private Long Id;
    private String name;
    private String EmailAddress;
    private List<Long> ArticleIdList;

    public Member(Long userNumber, String name, String emailAddress, List<Long> articleNumberList) {
        this.Id = userNumber;
        this.name = name;
        EmailAddress = emailAddress;
        ArticleIdList = articleNumberList;
    }

    public Long getId() {
        return Id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Long id) {
        this.Id = id;
    }

    public String getEmailAddress() {
        return EmailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        EmailAddress = emailAddress;
    }

    public List<Long> getArticleIdList() {
        return ArticleIdList;
    }

    public void setArticleIdList(List<Long> articleIdList) {
        ArticleIdList = articleIdList;
    }

    @Override
    public String toString() {
        return "Member{" +
                "userNumber=" + Id +
                ", nickName='" + name + '\'' +
                ", EmailAddress='" + EmailAddress + '\'' +
                ", ArticleNumberList=" + ArticleIdList +
                '}';
    }
    

}
