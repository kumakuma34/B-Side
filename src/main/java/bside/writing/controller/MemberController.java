package bside.writing.controller;

import bside.writing.Member.Member;
import bside.writing.Repository.MemberRepository;
import bside.writing.Service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
public class MemberController {
    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @RequestMapping(value = "/members", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public List<Member> getAllMembers(){
        List<Member> allMembers = memberRepository.findAll();
        return allMembers;
    }

    @PostMapping(value = "/members")
    public String create(@RequestBody Member member, HttpServletResponse response){
        if(!memberService.validateDuplicateMember(member)){
            response.setStatus(406);
            return "Duplicate User";
        }
        else{
            memberService.join(member);
            return member.toString();
        }
    }
    /* POSTMAN API 호출 테스트
    @RequestMapping(value = "/api", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public String getHadoopData(){
        return "tmp";
    }*/
}
