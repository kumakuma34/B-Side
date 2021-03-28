package bside.writing.controller;

import bside.writing.Service.MemberService;
import bside.writing.Service.TokenService;
import bside.writing.dto.MemberDto;
import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
public class MemberController {
    private final TokenService tokenService;
    private final MemberService memberService;

    @CrossOrigin("*")
    @RequestMapping(value = "/member", method = RequestMethod.PUT)
    public String memberUpdate(@RequestBody MemberDto memberDto, @RequestHeader(name="Authorization") String accessToken){
        JsonObject jsonResponse = new JsonObject();

        Long uid = tokenService.getUid(accessToken);
        memberService.update(uid, memberDto.getNickName(), memberDto.getProfileUrl());
        jsonResponse.addProperty("uid", uid);

        return jsonResponse.toString();
    }

    @CrossOrigin("*")
    @RequestMapping(value = "/member", method = RequestMethod.GET)
    public String memberInfo(@RequestHeader(name = "Authorization") String accessToken) {
        JsonObject jsonResponse = new JsonObject();

        Long uid = tokenService.getUid(accessToken);
        MemberDto memberDto = memberService.findById(uid);

        jsonResponse.addProperty("email", memberDto.getEmail());
        jsonResponse.addProperty("nick_name", memberDto.getNickName());
        jsonResponse.addProperty("profile_url", memberDto.getProfileUrl());
        return jsonResponse.toString();
    }

}
