package bside.writing.controller;

import bside.writing.Service.BadgeService;
import bside.writing.Service.TokenService;
import bside.writing.domain.badge.Badge;
import bside.writing.dto.BadgeDto;
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
        try{
            badgeList = badgeService.getBadgesByMemberId(memberId);
            List<BadgeDto> badgeDtoList = badgeService.toDtoList(badgeList);
            return badgeService.updateList(badgeDtoList);
        }
        catch (NoSuchElementException e){
            return badgeService.getDefaultResponse();
        }
    }
}
