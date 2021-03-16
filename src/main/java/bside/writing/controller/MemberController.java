package bside.writing.controller;

import bside.writing.Service.MemberService;
import bside.writing.Service.TokenService;
import bside.writing.domain.member.Member;
import bside.writing.templateClass.ResponseMessage;
import bside.writing.templateClass.StatusCode;
import com.google.gson.JsonObject;
import io.jsonwebtoken.security.SignatureException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final TokenService tokenService;
    private final MemberService memberService;

    @RequestMapping(value = "member/logout", method = RequestMethod.POST)
    public String memberLogout(@RequestHeader(name="Authorization") String accessToken, HttpServletResponse response){
        JsonObject jsonResponse = new JsonObject();
        try{
            Long memberId = tokenService.getUid(accessToken);
            jsonResponse.addProperty("uid", memberId);
            tokenService.deleteMemberToken(memberId);
            response.setStatus(StatusCode.OK.getCode());
        }
        catch (SignatureException e){
            jsonResponse.addProperty("error_msg", ResponseMessage.UNAUTHORIZED_TOKEN.getMsg());
            response.setStatus(StatusCode.UNAUTHORIZED.getCode());
        }
        return jsonResponse.toString();
    }

    @RequestMapping(value = "member", method = RequestMethod.PUT)
    public String memberUpdate(@RequestBody Member member, @RequestHeader(name="Authorization") String accessToken, HttpServletResponse response){
        JsonObject jsonResponse = new JsonObject();
        try{
            Long uid = tokenService.getUid(accessToken);
            memberService.update(uid, member.getNickName(), member.getProfileUrl());
            response.setStatus(StatusCode.OK.getCode());
            jsonResponse.addProperty("uid", uid);
        }
        catch (Exception e){
            response.setStatus(StatusCode.OK.getCode());
            jsonResponse.addProperty("error_msg", ResponseMessage.UNAUTHORIZED_TOKEN.getMsg());
        }
        return jsonResponse.toString();
    }

}
