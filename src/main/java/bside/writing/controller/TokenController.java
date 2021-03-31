package bside.writing.controller;

import bside.writing.Service.MemberService;
import bside.writing.Service.TokenService;
import bside.writing.dto.MemberDto;
import bside.writing.dto.MemberTokenDto;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
public class TokenController {

    private final TokenService tokenService;
    private final MemberService memberService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Map<String, Object> getToken(@RequestHeader(name="Authorization") String idTokenString) throws Exception {
        Map<String, Object> response = new HashMap<>();

        MemberDto memberDto = tokenService.getMemberDto(idTokenString);
        Boolean signIn = !memberService.has(memberDto.getEmail());
        memberDto = signIn ? memberService.join(memberDto) : memberService.findByEmail(memberDto.getEmail());

        String accessToken = tokenService.makeAccessToken(memberDto.getId());
        String refreshToken = tokenService.makeRefreshToken(memberDto.getId());

        MemberTokenDto memberTokenDto = MemberTokenDto.builder()
                .id(memberDto.getId())
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();

        tokenService.saveMemberToken(memberTokenDto);

        response.put("access_token", accessToken);
        response.put("refresh_token", refreshToken);
        response.put("sign_in", signIn);
        return response;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public Map<String, Object> memberLogout(@RequestHeader(name="Authorization") String accessToken){
        Map<String, Object> response = new HashMap<>();

        Long memberId = tokenService.getUid(accessToken);
        tokenService.deleteMemberToken(memberId);

        response.put("uid", memberId);
        return response;
    }

    @RequestMapping(value = "/token", method = RequestMethod.GET)
    public Map<String, Object> refreshAccessToken(@RequestHeader(name = "Authorization") String refreshToken){
        Map<String, Object> response = new HashMap<>();

        String accessToken = tokenService.refreshAccessToken(refreshToken);

        response.put("access_token", accessToken);
        return response;
    }
}
