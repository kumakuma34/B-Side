package bside.writing.Service;

import bside.writing.Repository.BadgeRepository;
import bside.writing.domain.badge.Badge;
import bside.writing.dto.BadgeDto;
import bside.writing.dto.BadgeSaveDto;
import bside.writing.enums.BadgeCode;
import bside.writing.dto.BadgeResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BadgeService {

    @Value("${SERVER_BADGE_URL}")
    private String SERVER_URL;
    private String imgFileExtension = ".svg";

    private final BadgeRepository badgeRepository;


    @Transactional
    public Badge save(BadgeSaveDto badgeSaveDto){
        return badgeRepository.save(badgeSaveDto.toEntity());
    }

    @Transactional
    public BadgeDto increaseBadgeValue(Long memberId, BadgeCode badgeCode){
        Badge badge = badgeRepository.findByMemberIdAndBadgeCode(memberId, badgeCode)
                .orElseThrow(() -> new NoSuchElementException("no badge"));
        badge.increaseBadgeValue();
        return new BadgeDto(badge);
    }

    @Transactional
    public BadgeDto decreaseBadgeValue(Long memberId, BadgeCode badgeCode){
        Badge badge = badgeRepository.findByMemberIdAndBadgeCode(memberId, badgeCode)
                .orElseThrow(() -> new NoSuchElementException("no badge"));
        badge.decreaseBadgeValue();
        return new BadgeDto(badge);
    }

    @Transactional
    public BadgeDto getBadge(Long memberId, BadgeCode badgeCode){
        return new BadgeDto(badgeRepository.findByMemberIdAndBadgeCode(memberId, badgeCode)
                .orElseThrow(() -> new NoSuchElementException("no badge")));
    }

    @Transactional
    public List<BadgeDto> getBadgeList(Long memberId){
        List<Badge> result = badgeRepository.findByMemberId(memberId);
        if(result.size() == 0)
            throw new NoSuchElementException("no badge");
        return toDtoList(result);
    }

    public List<BadgeDto> toDtoList(List<Badge> badgeList){
        return  badgeList.stream().map((entity) -> new BadgeDto(entity))
                .collect(Collectors.toList());
    }

    public Map<String, List> makeResponseList(long memberId){
        Map<String, List> response = new LinkedHashMap<>();

        for (BadgeCode curBadgeCode: BadgeCode.values()) {
            List curTypeBadgelist = new ArrayList();
            BadgeDto badge = getBadge(memberId, curBadgeCode);
            for (Integer curCriteriaValue: curBadgeCode.getCriteria()) {
                Map<String, Object> curBadge = new LinkedHashMap<>();
                curBadge.put("badge_value", curCriteriaValue);
                if(badge.getBadgeValue() >= curCriteriaValue){
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
