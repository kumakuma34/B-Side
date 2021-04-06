package bside.writing.controller;

import bside.writing.Service.ChallengeMemberService;
import bside.writing.Service.ChallengeService;
import bside.writing.Service.TokenService;
import bside.writing.domain.challenge.Challenge;
import bside.writing.dto.ChallengeDto;
import bside.writing.enums.ChallengeSearchCode;
import bside.writing.enums.ChallengeStatusCode;
import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class ChallengeController {

    private final ChallengeService challengeService;
    private final TokenService tokenService;
    private final ChallengeMemberService challengeMemberService;

    //challenge 신규 생성
    @CrossOrigin("*")
    @RequestMapping(value = "challenge", method = RequestMethod.POST)
    public Map<String, Object> saveChallenge(@RequestBody ChallengeDto.Request request, @RequestHeader(name="Authorization") String accessToken ) throws IOException{
        Long uid = tokenService.getUid(accessToken);
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("challenge_id", challengeService.addNewChallenge(request, uid).getChallengeId());
        return response;
    }

    //challenge 수정
    @CrossOrigin("*")
    @RequestMapping(value = "challenge/{challenge_id}", method = RequestMethod.POST)
    public ChallengeDto.Response updateChallenge(@PathVariable String challenge_id , @RequestBody ChallengeDto.Request request, @RequestHeader(name="Authorization") String accessToken) throws IOException{
        Long uid  = tokenService.getUid(accessToken);
        request.setChallengeId(Long.valueOf(challenge_id));
        return challengeService.updateChallenge(request, uid);
    }

    //challenge 삭제
    @CrossOrigin("*")
    @RequestMapping(value = "challenge/{challenge_id}", method = RequestMethod.DELETE)
    public String deleteChallenge(@PathVariable String challenge_id , @RequestHeader(name="Authorization") String accessToken) throws IOException{
        Long uid  = tokenService.getUid(accessToken);
        challengeService.deleteChallenge(Long.valueOf(challenge_id));
        return challenge_id + " deleted";
    }

    //challenge 조회(비그인)
    @CrossOrigin("*")
    @RequestMapping(value = "challenge/noLogin", method = RequestMethod.GET)
    public List<ChallengeDto.Response> getChallengeNoLogin() throws IOException{
        return challengeService.getSearchResult(ChallengeStatusCode.RECRUITING.getVal(),-1L);//모집중인 챌린지만 조회
    }

    //challenge 조회(로그인)
    @CrossOrigin("*")
    @RequestMapping(value = "challenge", method = RequestMethod.GET)
    public Map<String, Object> getChallenge(@RequestHeader(name="Authorization") String accessToken) throws IOException{
        Map<String, Object> response = new LinkedHashMap<>();

        Long uid = tokenService.getUid(accessToken);
        List<ChallengeDto.Response> result = new ArrayList<>();

        List<ChallengeDto.Response> inProgress = challengeService.getSearchResult(ChallengeStatusCode.IN_PROGRESS.getVal(), uid);
        List<ChallengeDto.Response> recruiting = challengeService.getSearchResult(ChallengeStatusCode.RECRUITING.getVal(), uid);

        response.put("recruiting" , recruiting);
        response.put("inProgress", inProgress);

        return response;
    }


    //challenge join
    @CrossOrigin("*")
    @RequestMapping(value = "challenge/join/{challenge_id}", method = RequestMethod.POST)
    public String joinChallenge(@PathVariable String challenge_id , @RequestHeader(name="Authorization") String accessToken) throws IOException{
        Long uid  = tokenService.getUid(accessToken);
        challengeService.increaseParticipant(Long.valueOf(challenge_id), uid);
        return "Success!!";
    }

    //challenge join in detail
    @CrossOrigin("*")
    @RequestMapping(value = "challenge/{challenge_id}", method = RequestMethod.GET)
    public ChallengeDto.Response getChallengeDetail(@PathVariable String challenge_id , @RequestHeader(name="Authorization") String accessToken) throws IOException{
        Long uid  = tokenService.getUid(accessToken);
        return challengeService.getChallengeDetail(Long.valueOf(challenge_id), uid);
    }


}
