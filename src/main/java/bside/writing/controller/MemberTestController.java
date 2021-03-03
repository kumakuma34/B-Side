package bside.writing.controller;

import bside.writing.Repository.MemberRepository;
import bside.writing.Service.MemberService;
import org.springframework.web.bind.annotation.*;

@RestController
public class MemberTestController {
    private final MemberService memberService;
    private final MemberRepository memberRepository;

    public MemberTestController(MemberService memberService, MemberRepository memberRepository) {
        this.memberService = memberService;
        this.memberRepository = memberRepository;
    }
}
