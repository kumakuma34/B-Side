package bside.writing.Member;


import org.springframework.context.annotation.Bean;
import org.springframework.data.annotation.Id;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.List;

@Entity
public class Member {
    @javax.persistence.Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long Id;

    @Column(length = 100)
    private String name;
    @Column(length = 100)
    private String EmailAddress;
//    @Column(length = 100)
//    private List<Long> ArticleIdList;
/*
    public Member(Long userNumber, String name, String emailAddress) {
    //public Member(Long userNumber, String name, String emailAddress, List<Long> articleNumberList) {
        this.Id = userNumber;
        this.name = name;
        EmailAddress = emailAddress;
        //ArticleIdList = articleNumberList;
    }*/
    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailAddress() {
        return EmailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        EmailAddress = emailAddress;
    }

//    public List<Long> getArticleIdList() {
//        return ArticleIdList;
//    }
//
//    public void setArticleIdList(List<Long> articleIdList) {
//        ArticleIdList = articleIdList;
//    }

    @Override
    public String toString() {
        return "Member{" +
                "userNumber=" + Id +
                ", nickName='" + name + '\'' +
                ", EmailAddress='" + EmailAddress + '\'' +
                //", ArticleNumberList=" + ArticleIdList +
                '}';
    }


}
