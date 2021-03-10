package bside.writing.Repository;

import bside.writing.domain.member.Member;
import bside.writing.Service.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    Member sample = Member.builder()
            .email_address("sample@dot.com")
            .nick_name("장현수")
            .user_role("betrayer")
            .picture_url("www.naver.com")
            .build();

    @Test
    public void 회원가입_테스트() throws Exception{
        System.out.println("sample = " + sample);
        Member result = memberService.join(sample);
        Assertions.assertThat(sample).isEqualTo(result);
    }

    @Test
    public void email로_유저조회() throws Exception{
        memberService.join(sample);
        Member member = memberService.findUserByEmail(sample.getEmail_address()).get();
        Assertions.assertThat(member).isEqualTo(sample);
    }

    @Test
    public void 유저정보_업데이트(){

    }


}
