package bside.writing.Service;

import bside.writing.domain.member.Member;
import bside.writing.Repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public Member join(Member member) {
        memberRepository.save(member);
        return member;
    }

    public Optional<Member> findUserByEmail(String userEmail){
        return memberRepository.findByUserEmail(userEmail);
    }

    public Member updateUserNameAndProfile(Member member){
        Member oldMember = memberRepository.findByUserEmail(member.getEmail()).get();
        return oldMember;
    }

    /*
    @Override
    public Optional<List<Member>> findByUserName(String userName) {
        String queryString = "select m from member m where m.name = :name";
        return findOrEmpty(()->
                em.createQuery(queryString, Member.class)
                        .setParameter("name", userName)
                        .getResultList()
        );
    }
    */
}

