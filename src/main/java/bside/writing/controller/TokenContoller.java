package bside.writing.controller;

import bside.writing.Service.MemberService;
import bside.writing.Service.TokenService;
import bside.writing.dto.MemberDto;
import bside.writing.templateClass.ResponseMessage;
import bside.writing.templateClass.StatusCode;
import com.google.api.client.json.jackson.JacksonFactory;
import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import netscape.javascript.JSObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.http.HttpResponse;
import java.security.GeneralSecurityException;
import java.util.Collections;

@RestController
@RequiredArgsConstructor
public class TokenContoller {

    private final TokenService tokenService;
    private final MemberService memberService;

    @RequestMapping(value = "token", method = RequestMethod.GET)
    public String getAccessToken(@RequestHeader(name="Authorization") String idTokenString, HttpServletResponse response){
        JsonObject jsonResponse = new JsonObject();
        try{
            MemberDto memberDto = tokenService.getUserInfo(idTokenString);

            boolean firstJoin = false;
            if(!memberService.has(memberDto.getEmail())){
                firstJoin = true;
                memberService.join(memberDto);
            }
            memberDto = memberService.findByEmail(memberDto.getEmail());

            jsonResponse.addProperty("accessToken", tokenService.makeAccessToken(memberDto.getId()));
            jsonResponse.addProperty("first_join", firstJoin);

            response.setStatus(StatusCode.OK.getCode());
            return jsonResponse.toString();
        }
        catch (Exception e){
            jsonResponse.addProperty("error_msg",ResponseMessage.UNAUTHORIZED_TOKEN.getMsg());

            response.setStatus(StatusCode.UNAUTHORIZED.getCode());
            return jsonResponse.toString();
        }
    }
}
