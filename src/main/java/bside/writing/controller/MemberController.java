package bside.writing.controller;

import bside.writing.Service.MemberService;
import bside.writing.Service.TokenService;
import bside.writing.domain.member.Member;
import bside.writing.dto.MemberDto;
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
    public String memberLogout(@RequestHeader(name="Authorization") String accessToken){
        JsonObject jsonResponse = new JsonObject();

        Long memberId = tokenService.getUid(accessToken);
        tokenService.deleteMemberToken(memberId);

        jsonResponse.addProperty("uid", memberId);
        return jsonResponse.toString();
    }

    @RequestMapping(value = "member", method = RequestMethod.PUT)
    public String memberUpdate(@RequestBody MemberDto memberDto, @RequestHeader(name="Authorization") String accessToken){
        JsonObject jsonResponse = new JsonObject();

        Long uid = tokenService.getUid(accessToken);
        memberService.update(uid, memberDto.getNickName(), memberDto.getProfileUrl());
        jsonResponse.addProperty("uid", uid);

        return jsonResponse.toString();
    }

    //@RequestMapping(value = "member", method = RequestMethod.GET)
    //public String getMember

}
