package bside.writing.Service;

import bside.writing.domain.challenge.Challenge;
import bside.writing.Repository.ChallengeRepository;
import bside.writing.dto.ChallengeDto;
import bside.writing.enums.ThemeCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChallengeService {
    private final ChallengeRepository challengeRepository;
    private final ThemeService themeService;

    public ChallengeDto.AllInfo makeAllInfoDTO(ChallengeDto.Request request, Long uid){
        String[] token = request.getTheme_string().split(" ");
        int maxCnt = ThemeCode.MAX_THEME_COUNT.getVal();
        int maxLen = ThemeCode.MAX_THEME_LENGTH.getVal();
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
