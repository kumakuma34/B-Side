package bside.writing.domain.member;

import lombok.*;
import javax.persistence.*;
import java.io.Serializable;


@Getter
@NoArgsConstructor
@Table(name = "member")
@Entity
@ToString
@Builder
public class Member implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long member_id;

    @Column(name = "email_address", length = 40, nullable = false)
    private String email;

    @Column(name = "nick_name", length = 100, nullable = false)
    private String nickName;

    @Column(name = "profile_url", length = 255)
    private String profileUrl;

    @Column(name = "user_role", length = 30, nullable = false)
    private String userRole;

    public void update(String nickName, String profileUrl){
        this.nickName = nickName;
        this.profileUrl = profileUrl;
    }

    public void changeRoll(String userRole){
        this.userRole = userRole;
    }
}
