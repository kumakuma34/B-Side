package bside.writing.Service;

import bside.writing.Repository.ChallengeMemberRepository;
import bside.writing.domain.challenge.Challenge;
import bside.writing.domain.challenge.ChallengeMember;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChallengeMemberService {
    private final ChallengeMemberRepository challengeMemberRepository;

    public ChallengeMember joinChallenge(Long challenge_id, Long uid){
        ChallengeMember entity = ChallengeMember.builder()
                .challengeId(challenge_id)
                .memberId(uid)
                .submitArticleCnt(0L)
                .build();

        /*
        TODO : challenge current user ++
         */
        return challengeMemberRepository.save(entity);
    }
}
