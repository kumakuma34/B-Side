package bside.writing.domain.member;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Entity
@Table(name = "member")
public class MemberToken implements Serializable {
    @Id
    @Column(name = "member_id")
    private Long id;

    @Column(name = "access_token", length = 255, nullable = false)
    private String accessToken;

    @Column(name = "refresh_token", length = 255, nullable = false)
    private String refreshToken;

    @Builder
    public MemberToken(Long id, String accessToken, String refreshToken) {
        this.id = id;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
