package bside.writing.controller;

import bside.writing.Service.ChallengeMemberService;
import bside.writing.Service.ChallengeService;
import bside.writing.Service.TokenService;
import bside.writing.domain.challenge.Challenge;
import bside.writing.dto.ChallengeDto;
import bside.writing.enums.ChallengeSearchCode;
import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ChallengeController {

    private final ChallengeService challengeService;
    private final TokenService tokenService;
    private final ChallengeMemberService challengeMemberService;

    //challenge 신규 생성
    @CrossOrigin("*")
    @RequestMapping(value = "challenge", method = RequestMethod.POST)
    public Challenge saveChallenge(@RequestBody ChallengeDto.Request request, @RequestHeader(name="Authorization") String accessToken ) throws IOException{
        Long uid = tokenService.getUid(accessToken);
        return challengeService.addNewChallenge(challengeService.makeAllInfoDTO(request, uid));
    }

    //challenge 조회
    @CrossOrigin("*")
    @RequestMapping(value = "challenge", method = RequestMethod.GET)
    public List<ChallengeDto.Response> getChallenge(@RequestParam String search_type, @RequestHeader(name="Authorization") String accessToken) throws IOException{
        Long uid = tokenService.getUid(accessToken);
        int searchType = ChallengeSearchCode.valueOf(search_type).getVal();
        return challengeService.getSearchResult(searchType, uid);
    }


    //challenge join
    @CrossOrigin("*")
    @RequestMapping(value = "challenge/join/{challenge_id}", method = RequestMethod.POST)
    public String joinChallenge(@PathVariable String challenge_id , @RequestHeader(name="Authorization") String accessToken) throws IOException{
        Long uid  = tokenService.getUid(accessToken);
        challengeMemberService.joinChallenge(Long.valueOf(challenge_id), uid);
        return "Success!!";
    }
/*
TODO : 모집 중 챌린지 조회
TODO : 진행 중 챌린지 조회
 */

}
