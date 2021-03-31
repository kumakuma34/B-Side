package bside.writing.controller;

import bside.writing.Service.MemberService;
import bside.writing.Service.TokenService;
import bside.writing.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;


@RestController
@RequiredArgsConstructor
public class MemberController {
    private final TokenService tokenService;
    private final MemberService memberService;

    @CrossOrigin("*")
    @RequestMapping(value = "/member", method = RequestMethod.PUT)
    public Map<String, Object> memberUpdate(@RequestBody MemberDto memberDto, @RequestHeader(name="Authorization") String accessToken){
        Map<String, Object> response = new LinkedHashMap<>();

        Long memberId = tokenService.getUid(accessToken);
        memberDto.checkOrElseThrow();
        memberService.update(memberId, memberDto.getNickName(), memberDto.getProfileUrl());
        response.put("uid", memberId);

        return response;
    }

    @CrossOrigin("*")
    @RequestMapping(value = "/member", method = RequestMethod.GET)
    public Map<String, Object> memberInfo(@RequestHeader(name = "Authorization") String accessToken) {
        Map<String, Object> response = new LinkedHashMap<>();

        Long memberId = tokenService.getUid(accessToken);
        MemberDto memberDto = memberService.findById(memberId);

        response.put("email", memberDto.getEmail());
        response.put("nick_name", memberDto.getNickName());
        response.put("profile_url", memberDto.getProfileUrl());
        return response;
    }
}
