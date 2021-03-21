package bside.writing.controller;

import bside.writing.Service.BadgeService;
import bside.writing.Service.TokenService;
import bside.writing.domain.badge.Badge;
import bside.writing.dto.BadgeDto;
import com.google.api.client.http.HttpResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class BadgeController {
    private final BadgeService badgeService;
    private final TokenService tokenService;

    @RequestMapping(value = "badge", method = RequestMethod.GET)
    public List<Badge> getBadges(@RequestBody BadgeDto badgeDto, @RequestHeader(name="Authorization") String accessToken, HttpServletResponse response){
        Long memberId = tokenService.getUid(accessToken);
        if(badgeDto.getBadgeCode().equals("ALL")){
            return badgeService.getBadgesByMemberId(memberId);
        }
        return badgeService.getBadgeByMemberIdAndBadgeCode(memberId, badgeDto.getBadgeCode());
    }

}
