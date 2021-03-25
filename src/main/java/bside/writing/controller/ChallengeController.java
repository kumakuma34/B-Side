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
@CrossOrigin
public class ChallengeController {

    private final ChallengeService challengeService;
    private final TokenService tokenService;
    private final ChallengeThemeService challengeThemeService;
    //challenge 신규 생성
    @RequestMapping(value = "challenge", method = RequestMethod.POST)
    public String saveChallenge(@RequestBody ChallengeDto.Request request, @RequestHeader(name="Authorization") String accessToken , HttpServletResponse response) throws IOException{
        JsonObject jsonResponse = new JsonObject();
        long new_challenge_id = -1L;

        //challenge 생성
        try{
            Long uid = tokenService.getUid(accessToken);
            ChallengeDto.SaveDto saveDto = new ChallengeDto.SaveDto(request.getChallenge_info(), uid, uid);
            new_challenge_id = challengeService.addNewChallenge(saveDto);
            jsonResponse.addProperty("challenge_id", new_challenge_id);
        }
        catch(Exception e) {
            jsonResponse.addProperty("challenge_result", "Error");
            response.setStatus(StatusCode.BAD_REQUEST.getCode());
        }

        //challenge_theme 생성
        try{
            if(new_challenge_id == -1L){
                jsonResponse.addProperty("challenge_theme_result" , "challenge create error");
                response.setStatus(StatusCode.BAD_REQUEST.getCode());
            }
            else{
                String result = challengeThemeService.insertChallengeTheme(request.getTheme_string(), new_challenge_id);
                jsonResponse.addProperty("challenge_theme_result", result);
                response.setStatus(StatusCode.OK.getCode());
            }
        }
        catch(Exception e){
            jsonResponse.addProperty("challenge_theme_result", "Error");
            response.setStatus(StatusCode.BAD_REQUEST.getCode());
        }

        return jsonResponse.toString();
    }



//    @RequestMapping(value = "challenge", method = RequestMethod.POST)
//    public String updateChallenge(@RequestBody )
//    {
//
//    }

}
