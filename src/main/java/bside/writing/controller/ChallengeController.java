package bside.writing.controller;

import bside.writing.Service.ChallengeService;
import bside.writing.Service.TokenService;
import bside.writing.domain.challenge.Challenge;
import bside.writing.dto.ChallengeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class ChallengeController {

    private final ChallengeService challengeService;
    private final TokenService tokenService;

    //challenge 신규 생성
    @RequestMapping(value = "challenge", method = RequestMethod.PUT)
    public long saveChallenge(@RequestBody ChallengeDto.Request request, @RequestHeader(name="Authorization") String accessToken) throws IOException{
        try{
            Long uid = tokenService.getUid(accessToken);
            ChallengeDto.SaveDto saveDto = new ChallengeDto.SaveDto(request.getInfo(), uid, uid);

            return challengeService.addNewChallenge(saveDto);
        }catch(Exception e){
            return -1L;
        }
    }

//    @RequestMapping(value = "challenge", method = RequestMethod.POST)
//    public String updateChallenge(@RequestBody )
//    {
//
//    }

}
