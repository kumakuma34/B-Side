package bside.writing.Service;

import bside.writing.Repository.BadgeRepository;
import bside.writing.domain.badge.Badge;
import bside.writing.enums.BadgeCode;
import bside.writing.dto.BadgeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BadgeService {

    @Value("${SERVER_URL}")
    private String SERVER_URL;
    private final BadgeRepository badgeRepository;

    public Map<String, List> getDefaultResponse(){
        Map<String, List> defaultResponse = new LinkedHashMap<>();
        Arrays.stream(BadgeCode.values()).forEach(
                (code)->{
                    List list = new ArrayList();
                    for (Object badgeValue : code.getCriteria()){
                        Map<String, Object> cur = new LinkedHashMap<>();
                        cur.put("badge_value", badgeValue);
                        cur.put("achieve", false);
                        cur.put("image_url", SERVER_URL + code.name() + badgeValue + "-default.png");
                        list.add(cur);
                    }
                    defaultResponse.put(code.name(), list);
                }
        );
        return defaultResponse;
    }

    @Transactional
    public List<Badge> getBadgesByMemberId(Long memberId){
        Map<String, List> defaultResponse = getDefaultResponse();
        return badgeRepository.findByMemberId(memberId)
                .orElseThrow(()->new NoSuchElementException("user does not have any badge"));
    }

    @Transactional
    public List<Badge> getBadgeByMemberIdAndBadgeCode(Long memberId, String badgeCode){
        if(!Arrays.stream(BadgeCode.values()).anyMatch(curBadgeCode -> curBadgeCode.name().equals(badgeCode)))
            throw new IllegalArgumentException("no such badgecode");

        return badgeRepository.findByMemberIdAndBadgeCode(memberId, badgeCode)
                .orElseThrow(() -> new NoSuchElementException("user does not have any badge"));
    }

    public List<BadgeDto> toDtoList(List<Badge> badgeList){
        return  badgeList.stream().map((entity) -> BadgeDto.builder()
                .badgeCode(entity.getBadgeCode())
                .badgeValue(entity.getBadgeValue())
                .badgeUrl(SERVER_URL + entity.getBadgeCode() + entity.getBadgeValue() + ".png")
                .build())
                .collect(Collectors.toList());
    }

    public Map<String, List> listToMap(List<BadgeDto> badgeDtoList){
        Collections.sort(badgeDtoList);

        Map<String, List> badgeDtoMap = new LinkedHashMap<>();
        for(BadgeDto badgeDto : badgeDtoList){
            String badgeCodeStr = badgeDto.getBadgeCode();

            if(!badgeDtoMap.containsKey(badgeCodeStr))
                badgeDtoMap.put(badgeCodeStr, new ArrayList());
            List curCodeBadgelist = badgeDtoMap.get(badgeDto.getBadgeCode());
            List curBadge = Arrays.asList(badgeDto.getBadgeValue(), badgeDto.getBadgeUrl());
            curCodeBadgelist.add(curBadge);
        }

        return badgeDtoMap;
    }

    public Map<String, List> updateList(List<BadgeDto> badgeDtoList){
        Map<String, List> updatedResponse = getDefaultResponse();
        Collections.sort(badgeDtoList);

        for(int i = 0; i < badgeDtoList.size(); ++i){
            BadgeDto curBadgeDto = badgeDtoList.get(i);
            List curBadgeList = updatedResponse.get(curBadgeDto.getBadgeCode());

            int index = BadgeCode.valueOf(curBadgeDto.getBadgeCode()).getCriteria().indexOf(curBadgeDto.getBadgeValue());
            Map<String, Object> map = (Map<String, Object>) curBadgeList.get(index);
            map.replace("achieve", true);
            map.replace("image_url", SERVER_URL + curBadgeDto.getBadgeCode() + curBadgeDto.getBadgeValue() + ".png");
        }
        return updatedResponse;
    }

}
