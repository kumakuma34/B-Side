package bside.writing.Repository;

import bside.writing.domain.badge.Badge;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BadgeRepository extends JpaRepository<Badge, Long> {
    Optional<List<Badge>> findByMemberId(final Long memberId);
}
