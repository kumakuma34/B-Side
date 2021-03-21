package bside.writing.Service;

import bside.writing.Repository.BadgeRepository;
import bside.writing.domain.badge.Badge;
import bside.writing.domain.badge.BadgeCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@Service
public class BadgeService {
    private final BadgeRepository badgeRepository;

    @Transactional
    public List<Badge> getBadgesByMemberId(Long memberId){
        List<Badge> badgesList = badgeRepository.findByMemberId(memberId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 ID"));

        Collections.sort(badgesList);

        return badgesList;
    }

    @Transactional
    public List<Badge> getBadgeByMemberIdAndBadgeCode(Long memberId, String badgeCode){
       List<Badge> badgeList = badgeRepository.findByMemberIdAndBadgeCode(memberId, badgeCode)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 ID"));
       Collections.sort(badgeList);
       return badgeList;
    }


}
