package bside.writing.controller;

import bside.writing.Service.BadgeService;
import bside.writing.Service.TokenService;
import bside.writing.domain.badge.Badge;
import bside.writing.dto.BadgeResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
public class BadgeController {
    private final BadgeService badgeService;
    private final TokenService tokenService;

    @CrossOrigin("*")
    @RequestMapping(value = "member/badge", method = RequestMethod.GET)
    public Map<String, List> getBadges(@RequestHeader(name="Authorization") String accessToken){
        Long memberId = tokenService.getUid(accessToken);

        List<Badge> badgeList;

        return null;
    }
}
