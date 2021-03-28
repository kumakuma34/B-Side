package bside.writing.Repository;

import bside.writing.domain.notification.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    Optional<List<Notification>> findByMemberId(Long memberId);
}
