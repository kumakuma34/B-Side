package bside.writing.Service;

import bside.writing.Entity.Challenge;
import bside.writing.Repository.ChallengeRepository;
import bside.writing.Repository.MemberRepository;
import bside.writing.Repository.NewChallengeRepository;
import bside.writing.dto.ChallengeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
@RequiredArgsConstructor
public class ChallengeService {
    private final NewChallengeRepository challengeRepository;

    public List<ChallengeDto> search(int searchCount){
        List<Challenge> challenges = challengeRepository.findAll();
        
        Challenge entity = challenges.get(0);
        ChallengeDto dto = new ChallengeDto(entity);

        List<ChallengeDto> dtos = challenges.stream().map(e->new ChallengeDto(e)).collect(Collectors.toList());
        //stream으로 순회하면서 각 원소를 Challenge > DTO 로 바꿈.

        return dtos;
    }

    public Long addNewChallenge(ChallengeDto challengeDto) {
        Challenge challenge = challengeDto.toEntity();
        return challengeRepository.save(challenge).getChallenge_id();
    }
}
