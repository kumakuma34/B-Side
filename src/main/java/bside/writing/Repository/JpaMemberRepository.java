package bside.writing.Repository;

import bside.writing.Member.Member;

import javax.persistence.EntityManager;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository{

    private final EntityManager em;

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }


    @Override
    public Member save(Member member) {
        System.out.println("Jpa Called");
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> delete(Long idNumber) {
        return Optional.empty();
    }

    @Override
    public Optional<Member> findByUserNumber(Long userNumber) {
        return Optional.empty();
    }

    @Override
    public Optional<Member> findByUserName(String userName) {
        return Optional.empty();
    }
}
