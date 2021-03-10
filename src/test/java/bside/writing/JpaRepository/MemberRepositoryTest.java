package bside.writing.JpaRepository;

import bside.writing.domain.member.Member;
import bside.writing.domain.member.NewMemberRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MemberRepositoryTest {

    @Autowired
    NewMemberRepository newMemberRepository;

    @Test
    public void 맴버저장_불러오기(){
        Member sample = Member.builder()
                .email_address("sample@dot.com")
                .nick_name("장현수3")
                .user_role("betrayer3")
                .picture_url("www.naver.com")
                .build();
        newMemberRepository.save(sample);

    }

    @Test
    public void 맴버삭제(){
        newMemberRepository.deleteById(4L);
        
    }
}
