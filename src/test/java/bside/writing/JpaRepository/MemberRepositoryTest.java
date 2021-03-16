package bside.writing.JpaRepository;

import bside.writing.domain.member.Member;
import bside.writing.Repository.MemberRepository;

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
public class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    public void 맴버저장_및_이메일_조회(){
        String testEmail = "TCUser@test.com";
        Member sample = Member.builder()
                .email(testEmail)
                .nickName("sample")
                .userRole("sample")
                .profileUrl("www.sample.com")
                .build();
        memberRepository.save(sample);

        Member entity = memberRepository.findByEmail(testEmail)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않거나 중복 : " + testEmail));
        MemberDto memberDto = new MemberDto(entity);
        Assertions.assertThat(testEmail).isEqualTo(memberDto.getEmail());
    }
}
