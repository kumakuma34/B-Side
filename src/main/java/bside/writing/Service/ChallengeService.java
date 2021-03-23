package bside.writing.Service;

import bside.writing.Repository.ChallengeThemeRepository;
import bside.writing.domain.challenge.Challenge;
import bside.writing.Repository.ChallengeRepository;
import bside.writing.dto.ChallengeDto;
import com.sun.istack.FinalArrayList;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChallengeService {
    private final ChallengeRepository challengeRepository;
    private final ChallengeThemeRepository challengeThemeRepository;

    public ArrayList<ChallengeDto.AllInfo> searchAll(){
        List<Challenge> result = challengeRepository.findAllByStartDt();
        ArrayList<ChallengeDto.AllInfo> list = new ArrayList<>();

        for(int i = 0 ; i < result.size(); i++){
            Challenge entity = result.get(i);
            Long challenge_id = entity.getChallengeId();
            List<String> challenge_theme = challengeThemeRepository.findByChallengeId(challenge_id);
            list.add(ChallengeDto.AllInfo.builder()
                    .coverImg(entity.getCoverImg())
                    .challengeTitle(entity.getChallengeTitle())
                    .challengeDetail(entity.getChallengeDetail())
                    .maxParticipant(entity.getMaxParticipant())
                    .currentParticipant(entity.getCurrentParticipant())
                    .startDt(entity.getStartDt())
                    .duration(entity.getDuration())
                    .submitDaysCnt(entity.getSubmitDaysCnt())
                    .status(entity.getStatus())
                    .createdId(entity.getCreatedId())
                    .modifiedId(entity.getModifiedId())
                    .theme_string(challenge_theme)
                    .build());
        }

        return list;

    }

    public List<ChallengeDto> search(int searchCount){



        List<Challenge> challenges = challengeRepository.findAll();
        
        Challenge entity = challenges.get(0);
        ChallengeDto dto = new ChallengeDto(entity);

        List<ChallengeDto> dtos = challenges.stream().map(e->new ChallengeDto(e)).collect(Collectors.toList());
        //stream으로 순회하면서 각 원소를 Challenge > DTO 로 바꿈.

        return dtos;
    }

    public Long addNewChallenge(ChallengeDto.SaveDto challengeDto) {
        Challenge challenge = challengeDto.toEntity();
        return challengeRepository.save(challenge).getChallengeId();
    }
}
