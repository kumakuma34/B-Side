package bside.writing.Service;

import bside.writing.Member.Member;
import bside.writing.Repository.JpaMemberRepository;
import bside.writing.Repository.MemoryMemberRepository;
import bside.writing.Repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
@RestController
public class MemberService {
    private final MemberRepository memberRepository;
    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    public Long join(Member member) {
        memberRepository.save(member);
        return member.getId();
    }

    public void validateDuplicateMember(Member member) {
        List<Member> allMembers = memberRepository.findAll();
        String cmpEmailAddress = member.getEmailAddress();
        for(int i = 0 ; i < allMembers.size(); i++){
            if(cmpEmailAddress.equals(allMembers.get(i).getEmailAddress())){
                throw new IllegalStateException("이미 존재하는 회원입니다.");
            }
        }
        return;
    }

    public Optional<Member> withdrawal(Member member) {
        return memberRepository.delete(member.getId());
    }

    public boolean login(Member member) {
        return true;
    }

    public boolean logout(Member member) {
        return true;
    }

    @GetMapping("/react")
    public String reactTestPage() {
        return "hello";
    }
}

