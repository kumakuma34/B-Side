package bside.writing.controller;

import bside.writing.Service.ChallengeService;
import bside.writing.Service.TokenService;
import bside.writing.domain.challenge.Challenge;
import bside.writing.dto.ChallengeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ChallengeController {

    private final ChallengeService challengeService;
    private final TokenService tokenService;

    //challenge 신규 생성
    @RequestMapping(value = "challenge", method = RequestMethod.POST)
    public Challenge saveChallenge(@RequestBody ChallengeDto.Request request, @RequestHeader(name="Authorization") String accessToken ) throws IOException{
        Long uid = tokenService.getUid(accessToken);
        return challengeService.addNewChallenge(challengeService.makeAllInfoDTO(request, uid));
    }

    //challenge 조회
    @RequestMapping(value = "challenge", method = RequestMethod.GET)
    public List<ChallengeDto.AllInfo> getChallenge(@RequestBody ChallengeDto.GetRequest request, @RequestHeader(name="Authorization") String accessToken) throws IOException{
        Long uid = tokenService.getUid(accessToken);
        List<ChallengeDto.AllInfo> challengeList = new ArrayList<>();
        if(request.getSearch_type().equals("ALL")){
            challengeList = challengeService.searchOpenChallenge();
        }
        else if(request.getSearch_type().equals("IN")){
            challengeList = challengeService.searchInChallenge(uid);
        }
        /*
        TODO : 참여 중인 챌린지, 내가 개설한 챌린지 조회 함수 구현
         */
        return challengeList;
    }

}
