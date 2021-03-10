package bside.writing.JpaRepository;

import bside.writing.domain.member.Member;
import bside.writing.domain.member.NewMemberRepository;

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
                .email("sample3@dot.com")
                .nickName("장현수3")
                .userRole("betrayer3")
                .profileUrl("www.naver.com")
                .build();
        newMemberRepository.save(sample);

    }

    @Test
    public void 이메일로_맴버검색(){
         Member sample = newMemberRepository.findByEmail("sample3@dot.com");
         System.out.println("sample = " + sample);
    }

    @Test
    public void
}
