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

    @Value("${SERVER_BADGE_URL}")
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
        return badgeRepository.findByMemberId(memberId)
                .orElseThrow(()->new NoSuchElementException());
    }

    public List<BadgeDto> toDtoList(List<Badge> badgeList){
        return  badgeList.stream().map((entity) -> BadgeDto.builder()
                .badgeCode(entity.getBadgeCode())
                .badgeValue(entity.getBadgeValue())
                .badgeUrl(SERVER_URL + entity.getBadgeCode() + entity.getBadgeValue() + ".png")
                .build())
                .collect(Collectors.toList());
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

    public BadgeDto checkAndGetBadge(BadgeDto badgeDto){
        if(isAchieve(badgeDto) && !hasThisBadge(badgeDto)){
            addBadge(badgeDto);
            return badgeDto;
        }
        return null;
    }

    public boolean isAchieve(BadgeDto badgeDto){
        BadgeCode badgeCode = BadgeCode.valueOf(badgeDto.getBadgeCode());
        String curValue = badgeDto.getBadgeValue();

        if(badgeCode.getCriteria().indexOf(curValue) >= 0){
            return true;
        }
        return false;
    }

    public boolean hasThisBadge(BadgeDto badgeDto){
        Optional<Badge> badge = badgeRepository.findByMemberIdAndBadgeCodeAndBadgeValue(badgeDto.getMemberId(), badgeDto.getBadgeCode(), badgeDto.getBadgeValue());
        if(badge.isPresent()) return true;
        return false;
    }

    @Transactional
    public Badge addBadge(BadgeDto badgeDto){
        return badgeRepository.save(badgeDto.toEntity());
    }
}
