package bside.writing.Repository;

import bside.writing.Member.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository{

    private final EntityManager em;

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }


    @Override
    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> delete(Long idNumber) {

        return Optional.empty();
    }

    @Override
    public Optional<Member> findByUserNumber(Long userNumber) {
        Member member = em.find(Member.class , userNumber);
        return Optional.ofNullable(member);
    }

    @Override
    public List<Member> findByUserName(String userName) {
        List<Member> resultList = em.createQuery("SELECT Id FROM TABLE Member", Member.class).getResultList();
        return resultList;
    }
}
