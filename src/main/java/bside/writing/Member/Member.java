package bside.writing.Member;


import java.util.List;

public class Member {
    private Long userNumber;
    private String nickName;
    private String EmailAddress;
    private List<Long> ArticleNumberList;

    public Member(Long userNumber, String nickName, String emailAddress, List<Long> articleNumberList) {
        this.userNumber = userNumber;
        this.nickName = nickName;
        EmailAddress = emailAddress;
        ArticleNumberList = articleNumberList;
    }

    public Long getUserNumber() {
        return userNumber;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
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
                ", nickName='" + nickName + '\'' +
                ", EmailAddress='" + EmailAddress + '\'' +
                ", ArticleNumberList=" + ArticleNumberList +
                '}';
    }
    

}
