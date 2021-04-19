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
    public Badge addBadge(BadgeResponseDto badgeResponseDto){
        return badgeRepository.save(badgeResponseDto.toEntity());
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
    public BadgeDto getBadgeByMemberIdAndBadgeCode(Long memberId, BadgeCode badgeCode){
        return new BadgeDto(badgeRepository.findByMemberIdAndBadgeCode(memberId, badgeCode)
                .orElseThrow(() -> new NoSuchElementException("no badge")));
    }

    @Transactional
    public List<BadgeDto> getBadgesByMemberId(Long memberId){
        List<Badge> result = badgeRepository.findByMemberId(memberId);
        if(result.size() == 0)
            throw new NoSuchElementException("no badge");
        return toDtoList(result);
    }

    public List<BadgeDto> toDtoList(List<Badge> badgeList){
        return  badgeList.stream().map((entity) -> new BadgeDto(entity))
                .collect(Collectors.toList());
    }

    public Map<String, List> getDefaultResponse(){
        Map<String, List> defaultResponse = new LinkedHashMap<>();
        Arrays.stream(BadgeCode.values()).forEach(
                (code)->{
                    List list = new ArrayList();
                    for (Object badgeValue : code.getCriteria()){
                        Map<String, Object> cur = new LinkedHashMap<>();
                        cur.put("badge_value", badgeValue);
                        cur.put("achieve", false);
                        cur.put("image_url", SERVER_URL + code.name() + badgeValue + "-default" + imgFileExtension);
                        list.add(cur);
                    }
                    defaultResponse.put(code.name(), list);
                }
        );
        return defaultResponse;
    }


}
