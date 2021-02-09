package bside.writing.Service;

import bside.writing.Member.Member;
import bside.writing.Repository.JpaMemberRepository;
import bside.writing.Repository.MemoryMemberRepository;
import bside.writing.Repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@Service
public class MemberService {
    private final MemberRepository JpaMemberRepository;
    @Autowired
    public MemberService(MemberRepository JpaMemberRepository) {
        this.JpaMemberRepository = JpaMemberRepository;
    }

    public Long join(Member member){
        JpaMemberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member){
        JpaMemberRepository.findByUserName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    public Optional<Member> withdrawal(Member member){
        return JpaMemberRepository.delete(member.getId());
    }

    public boolean login(Member member){
        return true;
    }

    public boolean logout(Member member){
        return true;
    }
}
