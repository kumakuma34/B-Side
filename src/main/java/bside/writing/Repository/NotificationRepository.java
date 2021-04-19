package bside.writing.Repository;

import bside.writing.domain.notification.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    @Query(value = "select * from notification where noti_start_date < now()" +
            " and member_id = ?1 order by noti_start_date desc ", nativeQuery = true)
    Optional<List<Notification>> findByMemberId(Long memberId);
}
