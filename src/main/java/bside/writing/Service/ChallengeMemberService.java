package bside.writing.Service;

import bside.writing.Repository.ChallengeMemberRepository;
import bside.writing.domain.challenge.Challenge;
import bside.writing.domain.challenge.ChallengeMember;
import bside.writing.dto.ThemeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChallengeMemberService {
    private final ChallengeMemberRepository challengeMemberRepository;
    private final MemberService memberService;

    public ChallengeMember joinChallenge(Long challenge_id, Long uid){
        ChallengeMember entity = ChallengeMember.builder()
                .challengeId(challenge_id)
                .memberId(uid)
                .submitArticleCnt(0L)
                .build();

        //challengeService.increaseParticipant(challenge_id);

        return challengeMemberRepository.save(entity);
    }

    //TODO : challenge member list<id> 형태로 조회해서 반환
    public List<String> getMemberList(Long challenge_id){
        List<Long> idList = challengeMemberRepository.findAllByChallengeId(challenge_id);
        return idList.stream().map(e->memberService.findNameById(e)).collect(Collectors.toList());
    }

}
