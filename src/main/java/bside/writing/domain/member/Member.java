package bside.writing.domain.member;

import lombok.*;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;


@Getter
@NoArgsConstructor
@Table(name = "member")
@Entity
public class Member implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long member_id;

    @Column(length = 40, nullable = false)
    private String email_address;

    @Column(length = 100, nullable = false)
    private String nick_name;

    @Column(length = 255)
    private String profile_url;

    @Column(length = 30, nullable = false)
    private String user_role;

    @Builder
    public Member(String email_address, String nick_name, String user_role, String picture_url) {
        this.email_address = email_address;
        this.nick_name = nick_name;
        this.user_role = user_role;
        this.profile_url = picture_url;
    }

}
