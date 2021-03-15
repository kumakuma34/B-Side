package bside.writing.controller;

import bside.writing.Repository.MemberRepository;
import bside.writing.Service.MemberService;
import bside.writing.Service.TokenService;
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
    public String memberLogout(@RequestHeader(name="Authorization") String accessToken, HttpServletResponse response){
        JsonObject jsonResponse = new JsonObject();
        try{
            Long memberUID = tokenService.getUID(accessToken);


            jsonResponse.addProperty("uid", memberUID);

            /*
            *   delete acessToken from DB
            */

            response.setStatus(StatusCode.OK.getCode());
            return jsonResponse.toString();
        }
        catch (SignatureException e){
            jsonResponse.addProperty("error_msg", ResponseMessage.UNAUTHORIZED_TOKEN.getMsg());

            response.setStatus(StatusCode.UNAUTHORIZED.getCode());
            return jsonResponse.toString();
        }
    }

}
