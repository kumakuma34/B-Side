package bside.writing.memberServiceTest;

import bside.writing.Service.MemberService;
import bside.writing.domain.member.Member;
import bside.writing.dto.MemberDto;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired
    MemberService memberService;

    String testEmail = "testUser@test.com";
    Member sampleEntity = Member.builder()
            .email(testEmail)
            .nickName("test")
            .userRole("testUser")
            .profileUrl("www.test.com")
            .build();
    MemberDto sampleMemberDto = new MemberDto(sampleEntity);

    @Test
    public void 맴버서비스_조인() {
        MemberDto join = memberService.join(sampleMemberDto);
        Assertions.assertThat(join.getId()).isEqualTo(memberService.findByEmail(testEmail).getId());
    }

    @Test
    public void 맴버서비스_업데이트(){
        memberService.join(sampleMemberDto);
        memberService.update(testEmail, "updated5555Name", "www.update5555.com");
        MemberDto memberDto = memberService.findByEmail(testEmail);
        Assertions.assertThat(testEmail).isEqualTo(memberDto.getEmail());
    }

}
