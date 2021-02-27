package bside.writing.Repository;

import bside.writing.Member.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;
import static bside.writing.WritingApplication.findOrEmpty;

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

    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    @Override
    public Optional<Member> findByUserNumber(Long userNumber) {
        Member member = em.find(Member.class , userNumber);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<List<Member>> findByUserName(String userName) {
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", userName)
                .getResultList();
        return Optional.ofNullable(result);
        //[PR #15] JPA로 쿼리 없이 실행시킬 수 있는지 더 알아봐야됨
    }

    @Override
    public Optional<List<Member>> findByUserEmail(String userEmail) {
        List<Member> result = em.createQuery("select m from Member m where m.EmailAddress = :EmailAddress", Member.class)
                .setParameter("EmailAddress", userEmail)
                .getResultList();
        return Optional.ofNullable(result);
        //[PR #15] JPA로 쿼리 없이 실행시킬 수 있는지 더 알아봐야됨
    }

}
