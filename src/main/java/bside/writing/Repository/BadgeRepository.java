package bside.writing.Repository;

import bside.writing.domain.badge.Badge;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BadgeRepository extends JpaRepository<Long, Badge> {

}
