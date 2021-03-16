package bside.writing.controller;

import bside.writing.Entity.Challenge;
import bside.writing.Service.ChallengeService;
import bside.writing.dto.ChallengeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class ChallengeController {

    private final ChallengeService challengeService;

    @RequestMapping(value = "challenge", method = RequestMethod.POST)
    public long saveChallenge() throws IOException{
        /*
        TODO : Implement saveChallenge
         */

          return 1L;
//        try{
//            return challengeService.addNewChallenge(challengeDto).toString();
//        }
//        catch(Exception e){
//            System.out.println(e.toString());
//            return "Invalid request body";
//        }
    }

//    @RequestMapping(value = "challenge", method = RequestMethod.GET)
//    public List<Challenge> getChallenge(@RequestHeader(name = "cnt") String searchKey) throws IOException{
//
//    }

}
