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

    public boolean validateDuplicateMember(Member member) {
        boolean isduplicate = false;
        Optional<List<Member>> foundMembers = memberRepository.findByUserEmail(member.getEmailAddress());
        isduplicate = foundMembers.isPresent();
        //emailAddress 검색 결과가 존재하면 duplicate == true;
        return isduplicate;
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

