package bside.writing.Service;

import bside.writing.Member.Member;
import bside.writing.Repository.JpaMemberRepository;
import bside.writing.Repository.MemoryMemberRepository;
import bside.writing.Repository.MemberRepository;
<<<<<<< HEAD
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@Service
public class MemberService {
    private final MemberRepository memberRepository;
    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
=======
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class MemberService {
    private final MemberRepository memberRepository = new MemoryMemberRepository();
    //
>>>>>>> master

    public Long join(Member member){
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member){
       /* memberRepository.findByUserName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });*/

    }

    public Optional<Member> withdrawal(Member member){
        return memberRepository.delete(member.getId());
    }

    public boolean login(Member member){
        return true;
    }

    public boolean logout(Member member){
        return true;
    }

    @GetMapping("/react")
    public String reactTestPage(){
        return "hello";
    }
}
