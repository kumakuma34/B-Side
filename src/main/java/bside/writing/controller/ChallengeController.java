package bside.writing.controller;

import bside.writing.Entity.Challenge;
import bside.writing.Service.ChallengeService;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Optional;

@RestController
public class ChallengeController {

    private final ChallengeService challengeService;

    public ChallengeController(ChallengeService challengeService) {
        this.challengeService = challengeService;
    }

//    @RequestMapping(value = "challenge", method = RequestMethod.GET)
//    public Optional<Challenge> getChallenge(@RequestHeader(name = "cnt") String searchKey) throws IOException{
//
//    }

}
