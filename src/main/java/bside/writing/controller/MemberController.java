package bside.writing.controller;

import bside.writing.Service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberController {
    private final MemberService memberService;
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }


    /* POSTMAN API 호출 테스트
    @RequestMapping(value = "/api", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public String getHadoopData(){
        return "tmp";
    }*/
}
