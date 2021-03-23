package bside.writing.Service;

import bside.writing.Repository.ChallengeThemeRepository;
import bside.writing.domain.challenge.Challenge;
import bside.writing.Repository.ChallengeRepository;
import bside.writing.domain.challenge.ChallengeTheme;
import bside.writing.dto.ChallengeDto;
import bside.writing.enums.ThemeCode;
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
    private final ThemeService themeService;
    /*
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
*/
    public List<ChallengeDto> search(int searchCount){



        List<Challenge> challenges = challengeRepository.findAll();
        
        Challenge entity = challenges.get(0);
        ChallengeDto dto = new ChallengeDto(entity);

        List<ChallengeDto> dtos = challenges.stream().map(e->new ChallengeDto(e)).collect(Collectors.toList());
        //stream으로 순회하면서 각 원소를 Challenge > DTO 로 바꿈.

        return dtos;
    }

    public ChallengeDto.AllInfo makeAllInfoDTO(ChallengeDto.Request request, Long uid){
        String[] token = request.getTheme().split(" ");
        int maxCnt = ThemeCode.valueOf("MAX_THEME_COUNT").getVal();
        int maxLen = ThemeCode.valueOf("MAX_THEME_LENGTH").getVal();
        if(token.length > maxCnt){
            throw new IllegalArgumentException("Theme count must be under 3");
        }

        ArrayList<Long> themeId = new ArrayList<>();
        for(int i = 0 ; i < token.length; i++){
            if(token[i].length() > maxLen) {
                throw new IllegalArgumentException("Theme length must be under 10");
            }
            Long id = themeService.findOrSaveTheme(token[i].substring(1,token[i].length()));
            themeId.add(id);
        }

        return ChallengeDto.AllInfo.builder()
                .coverImg(request.getCoverImg())
                .challengeTitle(request.getChallengeTitle())
                .challengeDetail(request.getChallengeDetail())
                .maxParticipant(request.getMaxParticipant())
                .currentParticipant(request.getCurrentParticipant())
                .startDt(request.getStartDt())
                .duration(request.getDuration())
                .submitDaysCnt(request.getSubmitDaysCnt())
                .status(request.getStatus())
                .createdId(uid)
                .modifiedId(uid)
                .theme1(themeId.get(0))
                .theme2(themeId.get(1))
                .theme3(themeId.get(2))
                .build();
    }
    public Challenge addNewChallenge(ChallengeDto.AllInfo challengeDto) {
        Challenge challenge = challengeDto.toEntity();
        return challengeRepository.save(challenge);
    }
}
