package bside.writing.Repository;

import bside.writing.domain.member.Member;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

import static bside.writing.templateClass.TemplateMethodClass.findOrEmpty;

@Transactional
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
    public Optional<Member> delete(Member member) {
        return Optional.empty();
    }

    @Override
    public Optional<Member> findByUserNumber(Long userNumber) {
        Member member = em.find(Member.class , userNumber);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<List<Member>> findByUserName(String userName) {
        String queryString = "select m from member m where m.name = :name";
        return findOrEmpty(()->
                em.createQuery(queryString, Member.class)
                .setParameter("name", userName)
                .getResultList()
                );
    }

    @Override
    public Optional<Member> findByUserEmail(String userEmail) {
        String queryString = "select m from Member m where m.email_address = :userEmail";
        return findOrEmpty(() ->
                em.createQuery(queryString, Member.class)
                        .setParameter("userEmail", userEmail)
                        .setMaxResults(1)
                        .getSingleResult()
                );
    }

}
