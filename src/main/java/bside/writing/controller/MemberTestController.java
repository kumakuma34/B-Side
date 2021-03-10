package bside.writing.controller;

import bside.writing.Repository.MemberRepository;
import bside.writing.Service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MemberTestController {
    private final MemberService memberService;
    private final MemberRepository memberRepository;
}
