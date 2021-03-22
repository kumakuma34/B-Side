package bside.writing.controller;

import bside.writing.Service.MemberService;
import bside.writing.Service.TokenService;
import bside.writing.domain.member.MemberToken;
import bside.writing.dto.MemberDto;
import bside.writing.dto.MemberTokenDto;
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
public class TokenController {

    private final TokenService tokenService;
    private final MemberService memberService;

    @RequestMapping(value = "token", method = RequestMethod.GET)
    public String getToken(@RequestHeader(name="Authorization") String idTokenString, HttpServletResponse response) throws Exception {
        JsonObject jsonResponse = new JsonObject();

            MemberDto memberDto = tokenService.getUserInfo(idTokenString);

            boolean firstJoin = false;
            if(!memberService.has(memberDto.getEmail())){
                firstJoin = true;
                memberService.join(memberDto);
            }

            memberDto = memberService.findByEmail(memberDto.getEmail());
            String accessToken = tokenService.makeAccessToken(memberDto.getId());
            MemberTokenDto memberTokenDto = MemberTokenDto.builder()
                    .id(memberDto.getId()).accessToken(accessToken)
                    .build();

            tokenService.saveMemberToken(memberTokenDto);

            jsonResponse.addProperty("accessToken", accessToken);
            jsonResponse.addProperty("first_join", firstJoin);

            response.setStatus(StatusCode.OK.getCode());

        /*
        catch (Exception e){
            System.out.println("e.toString() = " + e.toString());
            jsonResponse.addProperty("error_msg",ResponseMessage.UNAUTHORIZED_TOKEN.getMsg());
            response.setStatus(StatusCode.UNAUTHORIZED.getCode());
        }*/
        return jsonResponse.toString();
    }
}
