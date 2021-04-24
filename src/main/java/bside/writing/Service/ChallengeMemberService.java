package bside.writing.Service;

import bside.writing.Repository.ChallengeMemberRepository;
import bside.writing.domain.challenge.Challenge;
import bside.writing.domain.challenge.ChallengeMember;
import bside.writing.dto.ThemeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChallengeMemberService {
    private final ChallengeMemberRepository challengeMemberRepository;
    private final MemberService memberService;

    //챌린지 중복 참여 여부 검사
    public boolean checkDuplicate(Long challenge_id , Long uid){
        List<Long> prev = challengeMemberRepository.findDuplicate(challenge_id,uid);
        if(prev.isEmpty()) return false;
        else return true;
    }

    //챌린지 참여 함수
    public ChallengeMember joinChallenge(Long challenge_id, Long uid){
        if(checkDuplicate(challenge_id, uid))
            throw new IllegalArgumentException("member already in this challenge"); //챌린지 참여 중복 여부 검사

        ChallengeMember entity = ChallengeMember.builder()
                .challengeId(challenge_id)
                .memberId(uid)
                .submitArticleCnt(0L)
                .build();

        return challengeMemberRepository.save(entity);
    }

    //챌린지 글 제출 횟수 증가 함수
    public void submitCntIncrease(Long challengeId , Long uid){
        Optional<ChallengeMember> entity = challengeMemberRepository.findByChallengeAndMember(challengeId,uid);
        entity.orElseThrow(()->new IllegalArgumentException("no such member in challenge"));
        ChallengeMember e = entity.get();
        e.increaseSubmitCnt();
        challengeMemberRepository.save(e);
    }

    //TODO : challenge member list<id> 형태로 조회해서 반환
    public List<String> getMemberList(Long challenge_id){
        List<Long> idList = challengeMemberRepository.findAllByChallengeId(challenge_id);
        return idList.stream().map(e->memberService.findNameById(e)).collect(Collectors.toList());
    }

}
