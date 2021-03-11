package bside.writing.dto;

import bside.writing.domain.member.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class MemberDto {
    private Long id;
    private String email;
    private String nickName;
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



}