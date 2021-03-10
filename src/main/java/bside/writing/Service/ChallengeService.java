package bside.writing.Service;

import bside.writing.Entity.Challenge;
import bside.writing.Member.Member;
import bside.writing.Repository.ChallengeRepository;
import bside.writing.Repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class ChallengeService {
    private final ChallengeRepository challengeRepository;

    public ChallengeService(ChallengeRepository challengeRepository) {
        this.challengeRepository = challengeRepository;
    }

    public Long join(Challenge challenge) {
        challengeRepository.save(challenge);
        return challenge.getChallenge_id();
    }


}
