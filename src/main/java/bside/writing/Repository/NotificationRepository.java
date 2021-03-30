package bside.writing.Repository;

import bside.writing.domain.notification.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    @Query(value = "select * from notification where reserve_date < now() or reserve_date is null order by modified_date desc ", nativeQuery = true)
    Optional<List<Notification>> findByMemberId(Long memberId);
}
