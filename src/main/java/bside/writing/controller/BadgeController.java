package bside.writing.controller;

import bside.writing.Service.BadgeService;
import bside.writing.Service.TokenService;
import bside.writing.domain.badge.Badge;
import bside.writing.dto.BadgeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class BadgeController {
    private final BadgeService badgeService;
    private final TokenService tokenService;

    @CrossOrigin("*")
    @RequestMapping(value = "member/badge", method = RequestMethod.GET)
    public Map<String, List> getBadges(@RequestParam(value="code") String badgeCode, @RequestHeader(name="Authorization") String accessToken){
        Long memberId = tokenService.getUid(accessToken);
        List<Badge> badgeList;

        if(badgeCode.equals("ALL"))
            badgeList = badgeService.getBadgesByMemberId(memberId);
        else
            badgeList = badgeService.getBadgeByMemberIdAndBadgeCode(memberId, badgeCode);

        List<BadgeDto> badgeDtoList = badgeService.toDtoList(badgeList);
        return badgeService.toMapByBadgeCode(badgeDtoList);
    }
}
