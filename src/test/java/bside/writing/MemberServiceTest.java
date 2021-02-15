package bside.writing;

import bside.writing.Member.Member;
import bside.writing.Repository.JpaMemberRepository;
import bside.writing.Repository.MemberRepository;
import bside.writing.Repository.MemoryMemberRepository;
import bside.writing.Service.MemberService;
import jdk.jshell.spi.ExecutionControlProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@SpringBootTest

public class MemberServiceTest {
    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
    public void join() throws Exception{
        //Given
        Member member = new Member("qgqg264", "qgqg264@gmail.com");

        //When
        Long saveId = memberService.join(member);
        System.out.println(saveId);
    }

    @Test
    public void findUserById() throws Exception{
        //Given
        Long findUserLId = Long.valueOf(12);

        Optional<Member> result= memberRepository.findByUserNumber(findUserLId);

        System.out.println(result.get().getName());

    }

    @Test
    public void findUserByName() throws Exception{
        //Given
        String searchName = "qgqg264";

        ////List<Member> result= memberRepository.findByUserName(searchName);

       // System.out.println(result.get(0).getName() + " " + result.get(0).getEmailAddress());

    }

    @Test
    public void duplicateTest() throws Exception{
        //Given
        String cmpEmail = "qgqg264@gmial.com";
        Member member = new Member("tmp", cmpEmail);
        boolean result = memberService.validateDuplicateMember(member);
        System.out.println(result);
        try{
            memberService.validateDuplicateMember(member);
        }finally{

        }

    }
}
