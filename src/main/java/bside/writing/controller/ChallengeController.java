package bside.writing.controller;

import bside.writing.Service.ChallengeService;
import bside.writing.Service.TokenService;
import bside.writing.domain.challenge.Challenge;
import bside.writing.dto.ChallengeDto;
import bside.writing.enums.ChallengeCode;
import bside.writing.enums.ChallengeSearchCode;
import com.google.gson.JsonObject;
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
    @CrossOrigin("*")
    @RequestMapping(value = "challenge", method = RequestMethod.POST)
    public Challenge saveChallenge(@RequestBody ChallengeDto.Request request, @RequestHeader(name="Authorization") String accessToken ) throws IOException{
        Long uid = tokenService.getUid(accessToken);
        return challengeService.addNewChallenge(challengeService.makeAllInfoDTO(request, uid));
    }

    //challenge 조회
    @CrossOrigin("*")
    @RequestMapping(value = "challenge", method = RequestMethod.GET)
    public List<ChallengeDto.AllInfo> getChallenge(@RequestParam String search_type, @RequestHeader(name="Authorization") String accessToken) throws IOException{
        Long uid = tokenService.getUid(accessToken);
        int searchType = ChallengeSearchCode.valueOf(search_type).getVal();
        return challengeService.getSearchResult(searchType, uid);
    }

    //challenge join
    @CrossOrigin("*")
    @RequestMapping(value = "challenge/join", method = RequestMethod.GET)
    public String joinChallenge(@RequestBody ChallengeDto.GetRequest request, @RequestHeader(name="Authorization") String accessToken) throws IOException{
        JsonObject jsonResponse = new JsonObject();


        return jsonResponse.toString();
    }
/*
TODO : 모집 중 챌린지 조회
TODO : 진행 중 챌린지 조회
TODO : 챌린지 조회 query param으로 변경
TODO : 챌린지 조회 글감 id>이름으로 바꿔서 받아오기
 */

}
