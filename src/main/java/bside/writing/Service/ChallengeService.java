package bside.writing.Service;

import bside.writing.Repository.ThemeRepository;
import bside.writing.domain.challenge.Challenge;
import bside.writing.Repository.ChallengeRepository;
import bside.writing.domain.member.Member;
import bside.writing.dto.ChallengeDto;
import bside.writing.enums.ChallengeCode;
import bside.writing.enums.ChallengeStatusCode;
import bside.writing.enums.ThemeCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Array;
import java.util.*;

@Service
@RequiredArgsConstructor
public class ChallengeService {
    private final ChallengeRepository challengeRepository;
    private final ThemeService themeService;
    private final MemberService memberService;
    private final ChallengeMemberService challengeMemberService;
    private final ThemeRepository themeRepository;
    private int searchCnt = ChallengeCode.DEFAULT_SEARCH_COUNT.getVal();

    //request validation
    public void checkRequest(ChallengeDto.Request request){
        if(request.getDuration() <= 0) throw new IllegalArgumentException("duration should be > 0");
        if(request.getMaxParticipant() <= 0) throw new IllegalArgumentException("max participant should be > 0");
    }

    //theme String parse
    public List<String> parseThemeString(String themeString){
        String[] token = themeString.split(", ");
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
            Long id = themeService.findOrSaveTheme(token[i]);
            themeId.add(id);
        }
        return Arrays.asList(token);
    }

    @Transactional
    public ChallengeDto.Response updateChallenge(ChallengeDto.Request request , Long id){
        checkRequest(request);
        Challenge challenge = challengeRepository.findById(request.getChallengeId())
                .orElseThrow(()-> new NoSuchElementException("no such challenge"));
        challenge.update(request);
        return new ChallengeDto.Response(challenge);
    }

    @Transactional
    public void deleteChallenge(Long id){
        challengeRepository.deleteById(id);
    }


    public Challenge RequestToEntity(ChallengeDto.Request request, Long uid) {
        checkRequest(request);
        return Challenge.builder()
                .challengeId(request.getChallengeId())
                .coverImg(request.getCoverImg())
                .challengeTitle(request.getChallengeTitle())
                .challengeDetail(request.getChallengeDetail())
                .maxParticipant(request.getMaxParticipant())
                .currentParticipant(0)
                .startDt(request.getStartDt())
                .duration(request.getDuration())
                .submitDaysCnt(request.getSubmitDaysCnt())
                .status(ChallengeStatusCode.RECRUITING.getVal())
                .createdId(uid)
                .modifiedId(uid)
                .theme(request.getTheme())
                .build();
    }
//    public ChallengeDto.Response makeAllInfoDTO(ChallengeDto.Request request, Long uid){
//        String[] token = request.getTheme_string().split(", ");
//        int maxCnt = ThemeCode.MAX_THEME_COUNT.getVal();
//        int maxLen = ThemeCode.MAX_THEME_LENGTH.getVal();
//        if(token.length > maxCnt){
//            throw new IllegalArgumentException("Theme count must be under 3");
//        }
//
//        ArrayList<Long> themeId = new ArrayList<>();
//        for(int i = 0 ; i < token.length; i++){
//            if(token[i].length() > maxLen) {
//                throw new IllegalArgumentException("Theme length must be under 10");
//            }
//            Long id = themeService.findOrSaveTheme(token[i]);
//            themeId.add(id);
//        }
//
//        String statusName = "";
//        if(request.getStatus() == 0) statusName = ChallengeStatusCode.RECRUITING.name();
//        else if(request.getStatus() == 1) statusName = ChallengeStatusCode.IN_PROGRESS.name();
//        else if(request.getStatus() == 2) statusName = ChallengeStatusCode.COMPLETE.name();
//        return ChallengeDto.Response.builder()
//                .challengeId(request.getChallengeId())
//                .coverImg(request.getCoverImg())
//                .challengeTitle(request.getChallengeTitle())
//                .challengeDetail(request.getChallengeDetail())
//                .maxParticipant(request.getMaxParticipant())
//                .currentParticipant(request.getCurrentParticipant())
//                .startDt(request.getStartDt())
//                .duration(request.getDuration())
//                .submitDaysCnt(request.getSubmitDaysCnt())
//                .status(request.getStatus())
//                .status_name(statusName)
//                .createdId(uid)
//                .modifiedId(uid)
//                .theme1(themeId.get(0))
//                .theme2(themeId.get(1))
//                .theme3(themeId.get(2))
//                .themeNames(Arrays.asList(token))
//                .build();
//    }

    public Challenge addNewChallenge(ChallengeDto.Request request , Long uid) {
        int title_len = ChallengeCode.CHALLENGE_TITLE_LENGTH.getVal();
        int detail_len = ChallengeCode.CHALLENGE_DETAIL_LENGTH.getVal();

        if(request.getChallengeTitle() == null || request.getChallengeTitle().equals("")){
            throw new IllegalArgumentException("challenge title must not be null");
        }
        if(request.getChallengeTitle().length() > title_len){
            throw new IllegalArgumentException("challenge title length must be under 39");
        }

        if(request.getChallengeDetail() == null || request.getChallengeDetail().equals("")){
            throw new IllegalArgumentException("challenge detail must not be null");
        }
        if(request.getChallengeDetail().length() > detail_len){
            throw new IllegalArgumentException("challenge detail length must be under 100");
        }

        Challenge challenge = this.RequestToEntity(request, uid);
        return challengeRepository.save(challenge);
    }

    //theme id > Name 변환 함수
    public ChallengeDto.Response setThemeName(ChallengeDto.Response response){
        ChallengeDto.Response result = response;
        result.setThemeNames(parseThemeString(response.getTheme()));
        return result;
    }



    public ChallengeDto.Response setUerInfo(ChallengeDto.Response response){
        response.setJoinMembers(challengeMemberService.getMemberList(response.getChallengeId()));
        return response;
    }

    //challenge 조회
    public Page<Challenge> searchChallenge(int searchType, Long member_id){
        Page<Challenge> list = null;
        if(searchType == 0) list = challengeRepository.findOpenChallenge(PageRequest.of(0,searchCnt));
        else if(searchType == 1) list = challengeRepository.findInChallenge(member_id,PageRequest.of(0,searchCnt));
        else if(searchType == 2) list = challengeRepository.findMyDoneChallenge(member_id, PageRequest.of(0,searchCnt));
        else if(searchType == 3) list = challengeRepository.findMyChallenge(member_id,PageRequest.of(0,searchCnt));
        return list;
    }

    //challenge 조회 결과 Response DTO로 변환
    public List<ChallengeDto.Response> getSearchResult(int searchType, Long member_id){
        List<Challenge> list = searchChallenge(searchType, member_id).getContent();
        List<ChallengeDto.Response> result = new ArrayList<>();
        list.forEach(e->result.add(new ChallengeDto.Response(e)));
        result.forEach(e->setThemeName(e));
        return result;
    }

    //challenge Participant 증가
    public void increaseParticipant(Long challenge_id, Long uid){
        Challenge challenge = challengeRepository.findById(challenge_id).orElseThrow(()-> new NoSuchElementException("no such Challenge"));
        challenge.increaseCurrentParticipant();
        challengeRepository.save(challenge);
        challengeMemberService.joinChallenge(challenge_id, uid);
    }

    //challenge detail Search
    public ChallengeDto.Response getChallengeDetail(Long challenge_id, Long uid){
        Challenge challenge = challengeRepository.findById(challenge_id).orElseThrow(()-> new NoSuchElementException("no such Challenge"));
        ChallengeDto.Response result = new ChallengeDto.Response(challenge);
        result.setThemeNames(parseThemeString(result.getTheme()));
        setUerInfo(result);
        result.setOwnerId(challenge.getCreatedId());
        result.setOwnerName(memberService.findNameById(challenge.getCreatedId()));
        if(uid == challenge.getCreatedId()) result.setIsOwner(true);
        else result.setIsOwner(false);
        return result;
    }



    /*
    TODO : 매일 일배치로 시작일자 도달한 챌린지 status 업데이트 (0>1)
    TODO : Enum reverse 가져올 수 있게 수정 가능?
     */

}
