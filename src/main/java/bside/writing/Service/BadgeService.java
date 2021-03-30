package bside.writing.Service;

import bside.writing.Repository.BadgeRepository;
import bside.writing.domain.badge.Badge;
import bside.writing.enums.BadgeCode;
import bside.writing.dto.BadgeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BadgeService {
    private final BadgeRepository badgeRepository;

    @Transactional
    public List<Badge> getBadgesByMemberId(Long memberId){
        return badgeRepository.findByMemberId(memberId)
                .orElseThrow(()->new NoSuchElementException("user does not have any badge"));
    }

    @Transactional
    public List<Badge> getBadgeByMemberIdAndBadgeCode(Long memberId, String badgeCode){
        return badgeRepository.findByMemberIdAndBadgeCode(memberId, badgeCode)
                .orElseThrow(() -> {
                    if (!Arrays.stream(BadgeCode.values()).anyMatch(curBadgeCode -> curBadgeCode.name().equals(badgeCode)))
                        return new IllegalArgumentException("no such badgecode");
                    else
                        return new NoSuchElementException("user does not have any badge");
                    }
                );
    }

    public List<BadgeDto> toDtoList(List<Badge> badgeList){

        return  badgeList.stream().map((entity) -> BadgeDto.builder()
                .badgeCode(entity.getBadgeCode())
                .badgeValue(entity.getBadgeValue())
                .build())
                .collect(Collectors.toList());
    }

    public Map<String, List> toMapByBadgeCode(List<BadgeDto> badgeDtoList){
        Collections.sort(badgeDtoList);

        Map<String, List> badgeDtoMap = new HashMap<>();

        for(BadgeDto badgeDto : badgeDtoList){
            String badgeCodeStr = badgeDto.getBadgeCode();

            if(!badgeDtoMap.containsKey(badgeCodeStr))
                badgeDtoMap.put(badgeCodeStr, new ArrayList());

            List list = badgeDtoMap.get(badgeDto.getBadgeCode());
            list.add(badgeDto.getBadgeValue());

        }


        return badgeDtoMap;
    }

}
