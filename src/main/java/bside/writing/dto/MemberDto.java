package bside.writing.dto;

import bside.writing.domain.member.Member;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.converter.HttpMessageNotReadableException;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberDto {

    private Long id;
    private String email;
    @JsonProperty("nick_name")
    private String nickName;
    @JsonProperty("profile_url")
    private String profileUrl;
    private String userRole;

    public MemberDto(Member entity){
        this.id = entity.getId();
        this.email = entity.getEmail();
        this.nickName = entity.getNickName();
        this.profileUrl = entity.getProfileUrl();
        this.userRole = entity.getUserRole();
    }


    public Member toEntity(){
        return Member.builder()
                .id(this.getId())
                .email(this.getEmail())
                .nickName(this.getNickName())
                .profileUrl(this.getProfileUrl())
                .userRole(this.getUserRole())
                .build();
    }

    public void checkOrElseThrow(){
        if(nickName == null || profileUrl == null)
            throw new HttpMessageNotReadableException("");
    }
}