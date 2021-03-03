package bside.writing.Member;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.*;
import java.beans.ConstructorProperties;
import java.time.LocalDateTime;

@Entity
@Table(name = "member")
@Getter
@Setter
@ToString
public class Member {
    @javax.persistence.Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long member_id;

    @Column(length = 40)
    private String email;

    @Column(length = 100)
    private String  name;

    @CreationTimestamp
    private LocalDateTime reg_dt;

    @Column(length = 255)
    private String userRole;

    @Column(length = 255)
    private String pictureURL;

    @ConstructorProperties({"email", "name", "userRole", "pictureURL"})
    public Member(String email , String name , String userRole, String pictureURL) {
        this.email = email;
        this.name = name;
        this.userRole = userRole;
        this.pictureURL = pictureURL;
    }
}
