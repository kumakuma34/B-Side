package bside.writing.controller;

import bside.writing.Service.MemberService;
import bside.writing.Service.TokenService;
import bside.writing.dto.MemberDto;
import bside.writing.dto.MemberTokenDto;
import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class TokenController {

    private final TokenService tokenService;
    private final MemberService memberService;

    @RequestMapping(value = "token", method = RequestMethod.GET)
    public String getToken(@RequestHeader(name="Authorization") String idTokenString) throws Exception {
        JsonObject jsonResponse = new JsonObject();

        MemberDto memberDto = tokenService.getMemberDto(idTokenString);
        memberDto = memberService.has(memberDto.getEmail()) ? memberService.findByEmail(memberDto.getEmail()) : memberService.join(memberDto);

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
        return jsonResponse.toString();
    }
}
