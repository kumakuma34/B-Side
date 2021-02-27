package bside.writing.controller;

import bside.writing.Service.TokenService;
import com.google.api.client.json.jackson.JacksonFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;

@RestController
public class TokenContoller {

    private final TokenService tokenService;

    public TokenContoller(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @RequestMapping(value = "accesstoken", method = RequestMethod.GET)
    public String getAccessToken(@RequestHeader(name="Authorization") String idTokenString) throws GeneralSecurityException, IOException {
        return tokenService.getAccessToken(idTokenString);
    }
}
