package bside.writing.controller;

import bside.writing.Service.MemberService;
import bside.writing.Service.TokenService;
import bside.writing.dto.MemberDto;
import bside.writing.dto.MemberTokenDto;
import com.google.api.client.json.Json;
import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class TokenController {

    private final TokenService tokenService;
    private final MemberService memberService;

    @CrossOrigin("*")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String getToken(@RequestHeader(name="Authorization") String idTokenString) throws Exception {
        JsonObject jsonResponse = new JsonObject();

        MemberDto memberDto = tokenService.getMemberDto(idTokenString);
        boolean signIn = !memberService.has(memberDto.getEmail());
        memberDto = signIn ? memberService.join(memberDto) : memberService.findByEmail(memberDto.getEmail());

        String accessToken = tokenService.makeAccessToken(memberDto.getId());
        String refreshToken = tokenService.makeRefreshToken(memberDto.getId());

        MemberTokenDto memberTokenDto = MemberTokenDto.builder()
                .id(memberDto.getId())
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();

        tokenService.saveMemberToken(memberTokenDto);

        jsonResponse.addProperty("access_token", accessToken);
        jsonResponse.addProperty("refresh_token", refreshToken);
        jsonResponse.addProperty("sign_in", signIn);
        return jsonResponse.toString();
    }

    @CrossOrigin("*")
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public String memberLogout(@RequestHeader(name="Authorization") String accessToken){
        JsonObject jsonResponse = new JsonObject();

        Long memberId = tokenService.getUid(accessToken);
        tokenService.deleteMemberToken(memberId);

        jsonResponse.addProperty("uid", memberId);
        return jsonResponse.toString();
    }

    @CrossOrigin("*")
    @RequestMapping(value = "/token", method = RequestMethod.GET)
    public String refreshAccessToken(@RequestHeader(name = "Authorization") String refreshToken){
        JsonObject jsonObject = new JsonObject();

        Long memberId = tokenService.getUid(refreshToken);
        String accessToken = tokenService.refreshAccessToken(refreshToken);

        jsonObject.addProperty("access_token", accessToken);
        return jsonObject.toString();
    }
}
