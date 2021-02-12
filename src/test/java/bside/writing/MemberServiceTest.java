package bside.writing;

import bside.writing.Member.Member;
import bside.writing.Repository.JpaMemberRepository;
import bside.writing.Repository.MemberRepository;
import bside.writing.Repository.MemoryMemberRepository;
import bside.writing.Service.MemberService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest

public class MemberServiceTest {
    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
    public void join() throws Exception{
        //Given
        Member member = new Member("HyunSoo", "qgqg264@gmial.com");

        //When
        Long saveId = memberService.join(member);
        System.out.println(saveId);
    }

    public void findUser() throws Exception{
        //Given
        String findUserName = "HyunSoo";

        List<Member> result = memberRepository.findByUserName(findUserName);

        System.out.println(result.size());

    }
}
