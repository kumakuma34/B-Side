package bside.writing.domain.member;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;


@Getter
@NoArgsConstructor
@Table(name = "member")
@Entity
@ToString
public class Member implements Serializable {

    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email_address", length = 40, nullable = false)
    private String email;

    @JsonProperty("nick_name")
    @Column(name = "nick_name", length = 100, nullable = false)
    private String nickName;

    @JsonProperty("profile_url")
    @Column(name = "profile_url", length = 255)
    private String profileUrl;

    @Column(name = "user_role", length = 30, nullable = false)
    private String userRole;

    public void update(String nickName, String profileUrl){
        this.nickName = nickName;
        this.profileUrl = profileUrl;
    }

    @Builder
    public Member(Long id, String email, String nickName, String userRole, String profileUrl) {
        this.id = id;
        this.email = email;
        this.nickName = nickName;
        this.userRole = userRole;
        this.profileUrl = profileUrl;
    }
}
