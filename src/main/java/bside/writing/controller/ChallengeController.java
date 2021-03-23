package bside.writing.controller;

import bside.writing.Service.ChallengeService;
import bside.writing.Service.ChallengeThemeService;
import bside.writing.Service.TokenService;
import bside.writing.domain.challenge.Challenge;
import bside.writing.dto.ChallengeDto;
import bside.writing.templateClass.StatusCode;
import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class ChallengeController {

    private final ChallengeService challengeService;
    private final TokenService tokenService;
    private final ChallengeThemeService challengeThemeService;
    //challenge 신규 생성
    @RequestMapping(value = "challenge", method = RequestMethod.POST)
    public Challenge saveChallenge(@RequestBody ChallengeDto.Request request, @RequestHeader(name="Authorization") String accessToken ) throws IOException{
        Long uid = tokenService.getUid(accessToken);

        return challengeService.addNewChallenge(challengeService.makeAllInfoDTO(request, uid));
    }

}
