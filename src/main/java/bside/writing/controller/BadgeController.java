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

    @RequestMapping(value = "member/badge", method = RequestMethod.GET)
    public Map<String, List> getBadges(@RequestBody BadgeDto badgeDto, @RequestHeader(name="Authorization") String accessToken){
        List<Badge> badgeList;
        List<BadgeDto> badgeDtoList;

        Long memberId = tokenService.getUid(accessToken);

        if(badgeDto.getBadgeCode().equals("ALL"))
            badgeList = badgeService.getBadgesByMemberId(memberId);
        else
            badgeList = badgeService.getBadgeByMemberIdAndBadgeCode(memberId, badgeDto.getBadgeCode());

        badgeDtoList = badgeService.toDtoList(badgeList);
        return badgeService.toMapByBadgeCode(badgeDtoList);
    }
}
