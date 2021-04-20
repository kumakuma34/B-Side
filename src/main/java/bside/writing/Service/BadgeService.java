package bside.writing.Service;

import bside.writing.Repository.BadgeRepository;
import bside.writing.domain.badge.Badge;
import bside.writing.dto.BadgeDto;
import bside.writing.dto.BadgeSaveDto;
import bside.writing.enums.BadgeCode;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@RequiredArgsConstructor
@Service
public class BadgeService {

    @Value("${SERVER_BADGE_URL}")
    private String SERVER_URL;
    private String imgFileExtension = ".svg";

    private final BadgeRepository badgeRepository;


    @Transactional
    private Badge save(BadgeSaveDto badgeSaveDto){
        return badgeRepository.save(badgeSaveDto.toEntity());
    }

    @Transactional
    public BadgeDto increaseBadgeCurValue(Long memberId, BadgeCode badgeCode){
        Badge badge = getBadgeEntity(memberId, badgeCode);
        badge.increaseBadgeCurValue();

        for (Integer curCriteria: badgeCode.getCriteria()) {
            if(badge.getBadgeCurValue() >= curCriteria){
                badge.setBadgeMaxValue(curCriteria);
            }
        }

        return new BadgeDto(badge);
    }

    @Transactional
    public BadgeDto decreaseBadgeCurValue(Long memberId, BadgeCode badgeCode){
        Badge badge = getBadgeEntity(memberId, badgeCode);
        badge.decreaseBadgeCurValue();
        return new BadgeDto(badge);
    }

    @Transactional
    public Badge getBadgeEntity(Long memberId, BadgeCode badgeCode){
        return badgeRepository.findByMemberIdAndBadgeCode(memberId, badgeCode.name())
                .orElseGet(()-> save(BadgeSaveDto.builder()
                        .memberId(memberId)
                        .badgeCode(badgeCode)
                        .build()));
    }

    @Transactional
    public BadgeDto getBadge(Long memberId, BadgeCode badgeCode){
        return new BadgeDto(badgeRepository.findByMemberIdAndBadgeCode(memberId, badgeCode.name())
                .orElseGet(()-> save(BadgeSaveDto.builder()
                        .memberId(memberId)
                        .badgeCode(badgeCode)
                        .build())));
    }

    public Map<String, List> makeResponse(long memberId){
        Map<String, List> response = new LinkedHashMap<>();

        for (BadgeCode curBadgeCode: BadgeCode.values()) {
            List curTypeBadgelist = new ArrayList();
            BadgeDto badge = getBadge(memberId, curBadgeCode);
            for (Integer curCriteriaValue: curBadgeCode.getCriteria()) {
                Map<String, Object> curBadge = new LinkedHashMap<>();
                curBadge.put("badge_value", curCriteriaValue);
                if(badge.getBadgeMaxValue() >= curCriteriaValue){
                    curBadge.put("achieve", true);
                    curBadge.put("image_url", SERVER_URL + curBadgeCode.name() + curCriteriaValue + imgFileExtension);
                }
                else{
                    curBadge.put("achieve", false);
                    curBadge.put("image_url", SERVER_URL + curBadgeCode.name() + curCriteriaValue + "-default" +  imgFileExtension);
                }
                curTypeBadgelist.add(curBadge);
            }
            response.put(curBadgeCode.name(), curTypeBadgelist);
        }
        return response;
    }

}
