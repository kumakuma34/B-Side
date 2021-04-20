package bside.writing.Repository;

import bside.writing.domain.badge.Badge;
import bside.writing.enums.BadgeCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BadgeRepository extends JpaRepository<Badge, Long> {
    List<Badge> findByMemberId(final Long memberId);

    Optional<Badge> findByMemberIdAndBadgeCode(final Long memberId, final String BadgeCode);

    Optional<Badge> findByMemberIdAndBadgeCodeAndBadgeValue(final Long memberId, final String BadgeCode, final String BadgeValue);
}
