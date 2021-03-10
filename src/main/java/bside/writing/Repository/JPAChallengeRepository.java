package bside.writing.Repository;

import bside.writing.Entity.Challenge;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;

@Component
public class JPAChallengeRepository implements ChallengeRepository{
    private final EntityManager em;
    public JPAChallengeRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Challenge save(Challenge challenge) {
        em.persist(challenge);
        return challenge;
    }
}
