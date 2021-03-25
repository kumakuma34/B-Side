package bside.writing.dto;

import bside.writing.domain.member.MemberToken;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class MemberTokenDto {
    private Long id;
    private String accessToken;
    private String refreshToken;

    public MemberTokenDto(MemberToken entity){
        this.id = entity.getId();
        this.accessToken = entity.getAccessToken();
        this.refreshToken = entity.getRefreshToken();
    }

    public MemberToken toEntity(){
        return MemberToken.builder()
                .id(id)
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
}
