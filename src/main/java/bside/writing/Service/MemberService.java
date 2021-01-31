package bside.writing.Service;

import bside.writing.Member.Member;
import bside.writing.Repository.MemoryMemberRepository;
import bside.writing.Repository.MemberRepository;

import java.util.Optional;

public class MemberService {
    private final MemberRepository memberRepository = new MemoryMemberRepository();

    public Long join(Member member){
        memberRepository.save(member);
        return member.getUserNumber();
    }

    private void validateDuplicateMember(Member member){
        memberRepository.findByUserName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    public Optional<Member> withdrawal(Member member){
        return memberRepository.delete(member.getUserNumber());
    }

    public boolean login(Member member){
        return true;
    }

    public boolean logout(Member member){
        return true;
    }
}
