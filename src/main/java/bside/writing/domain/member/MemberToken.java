package bside.writing.domain.member;

import bside.writing.templateClass.BaseTimeEntity;
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
public class MemberToken extends BaseTimeEntity implements Serializable {

    @Id
    @Column(name = "member_id", nullable = false)
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
