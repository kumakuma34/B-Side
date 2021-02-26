package bside.writing.controller;

import bside.writing.Member.Member;
import bside.writing.Repository.MemberRepository;
import bside.writing.Service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import bside.writing.Member.Member;
import bside.writing.Repository.MemberRepository;
import bside.writing.Service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
public class MemberController {
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    @RequestMapping(value = "/members", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public boolean findMember(@RequestParam String email_address){
        System.out.println(email_address);
        Optional<List<Member>> findResult = memberRepository.findByUserEmail(email_address);
        if(findResult.isPresent()) return true;
        else return false;
    }

    @PostMapping(value = "/members" )
    public String create(@RequestBody Member member, HttpServletResponse response) throws Exception{
        LocalDateTime curr = LocalDateTime.now();
        member.setReg_dt(curr);
        memberService.join(member);
        return member.toString();
        /*if(!memberService.validateDuplicateMember(member)){
            response.setStatus(406);
            return "Duplicate User";
        }
        else{
            memberService.join(member);
            return member.toString();
        }*/
    }

}
