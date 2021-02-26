package bside.writing.Member;


import com.fasterxml.jackson.annotation.JsonAnySetter;
import org.springframework.context.annotation.Bean;
import org.springframework.data.annotation.Id;
import com.fasterxml.jackson.annotation.JsonCreator;
import org.hibernate.annotations.CreationTimestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.*;
import java.beans.ConstructorProperties;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "member")
public class Member {
    @javax.persistence.Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long member_id;

    @Column(length = 40)
    private String email_address;

    @Column(length = 100)
    private String  nick_name;

    @CreationTimestamp //Audting 처리 예정
    private LocalDateTime reg_dt;

    @Column(length = 1)
    private String admin_yn;

    @Column(length = 255)
    private String userRole;

    @Column(length = 255)
    private String pictureURL;

    @ConstructorProperties({"email_address", "nick_name", "admin_yn", "userRole", "pictureURL"})
    //JSON Type <> Member Constructor
    public Member(String email_address , String nick_name , String admin_yn, String userRole, String pictureURL) {
        this.email_address = email_address;
        this.nick_name = nick_name;
        //this.reg_dt = reg_dt;
        this.admin_yn = admin_yn;
        this.userRole = userRole;
        this.pictureURL = pictureURL;
    }

    public Member(){};//default Constructor


    public Long getMember_id() {
        return member_id;
    }

    public void setMember_id(Long member_id) {
        this.member_id = member_id;
    }

    public String getEmail_address() {
        return email_address;
    }

    public void setEmail_address(String email_address) {
        this.email_address = email_address;
    }

    public String getNick_name() {
        return nick_name;
    }

    public void setNick_name(String nick_name) {
        this.nick_name = nick_name;
    }

    public LocalDateTime getReg_dt() {
        return reg_dt;
    }

    public void setReg_dt(LocalDateTime reg_dt) {
        this.reg_dt = reg_dt;
    }

    public String getAdmin_yn() {
        return admin_yn;
    }

    public void setAdmin_yn(String admin_yn) {
        this.admin_yn = admin_yn;
    }
    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getPictureURL() {
        return pictureURL;
    }

    public void setPictureURL(String pictureURL) {
        this.pictureURL = pictureURL;
    }
    @Override
    public String toString() {
        return "Member{" +
                "member_id=" + member_id +
                ", email_address='" + email_address + '\'' +
                ", nick_name='" + nick_name + '\'' +
                ", reg_dt=" + reg_dt +
                ", admin_yn='" + admin_yn + '\'' +
                '}';
    }
}
