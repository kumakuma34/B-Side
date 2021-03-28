package bside.writing.Service;

import bside.writing.domain.challenge.Challenge;
import bside.writing.Repository.ChallengeRepository;
import bside.writing.dto.ChallengeDto;
import bside.writing.enums.ChallengeCode;
import bside.writing.enums.ThemeCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChallengeService {
    private final ChallengeRepository challengeRepository;
    private final ThemeService themeService;
    private int searchCnt = ChallengeCode.DEFAULT_SEARCH_COUNT.getVal();

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
        int title_len = ChallengeCode.CHALLENGE_TITLE_LENGTH.getVal();
        int detail_len = ChallengeCode.CHALLENGE_DETAIL_LENGTH.getVal();

        if(challengeDto.getChallengeTitle() == null || challengeDto.getChallengeTitle().equals("")){
            throw new IllegalArgumentException("challenge title must not be null");
        }
        if(challengeDto.getChallengeTitle().length() > title_len){
            throw new IllegalArgumentException("challenge title length must be under 39");
        }

        if(challengeDto.getChallengeDetail() == null || challengeDto.getChallengeDetail().equals("")){
            throw new IllegalArgumentException("challenge detail must not be null");
        }
        if(challengeDto.getChallengeDetail().length() > detail_len){
            throw new IllegalArgumentException("challenge detail length must be under 100");
        }

        Challenge challenge = challengeDto.toEntity();
        return challengeRepository.save(challenge);
    }
    public Page<Challenge> searchChallenge(int searchType, Long member_id){
        Page<Challenge> list = null;
        if(searchType == 0) list = challengeRepository.findOpenChallenge(PageRequest.of(0,searchCnt));
        else if(searchType == 1) list = challengeRepository.findInChallenge(member_id,PageRequest.of(0,searchCnt));
        else if(searchType == 2) list = challengeRepository.findMyChallenge(member_id,PageRequest.of(0,searchCnt));
        else if(searchType == 3) list = challengeRepository.findMyDoneChallenge(member_id, PageRequest.of(0,searchCnt));
        return list;
    }

    public List<ChallengeDto.AllInfo> getSearchResult(int searchType, Long member_id){
        List<Challenge> list = searchChallenge(searchType, member_id).getContent();
        List<ChallengeDto.AllInfo> result = new ArrayList<>();
        list.forEach(e->result.add(new ChallengeDto.AllInfo(e)));
        return result;
    }

    public void increaseParticipant(Long challenge_id){
        Challenge challenge = challengeRepository.findById(challenge_id).orElseThrow(()-> new NoSuchElementException("no such Challenge"));
        challenge.increaseCurrentParticipant();
        challengeRepository.save(challenge);
    }

    /*
    TODO : 매일 일배치로 시작일자 도달한 챌린지 status 업데이트 (0>1)
     */



}
