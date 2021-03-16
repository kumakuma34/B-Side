package bside.writing.domain.member;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Entity
@Table(name = "token")
@NoArgsConstructor

public class MemberToken implements Serializable {
    @Id
    @Column(name = "member_id")
    private Long id;

    @Column(name = "access_token", length = 255, nullable = false)
    private String accessToken;

    @Column(name = "refresh_token", length = 255, nullable = true)
    private String refreshToken;

    @Builder
    public MemberToken(Long id, String accessToken, String refreshToken) {
        this.id = id;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public void update(String accessToken) {
        this.accessToken = accessToken;
    }
}
