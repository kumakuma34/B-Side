package bside.writing.Repository;

import bside.writing.Member.Member;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

import static bside.writing.templateClass.TemplateMethodClass.findOrEmpty;

@Component
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
        List<Member> result = em.createQuery("select m from member m where m.name = :name", Member.class)
                .setParameter("name", userName)
                .getResultList();
        return Optional.ofNullable(result);
        //[PR #15] JPA로 쿼리 없이 실행시킬 수 있는지 더 알아봐야됨
    }

    @Override
    public Optional<Member> findByUserEmail(String userEmail) {
        String queryString = "select m from Member m where m.email_address = :EmailAddress";
        return findOrEmpty(() ->
                em.createQuery(queryString, Member.class)
                        .setParameter("EmailAddress", userEmail)
                        .setMaxResults(1)
                        .getSingleResult());


        //[PR #15] JPA로 쿼리 없이 실행시킬 수 있는지 더 알아봐야됨
    }

}
