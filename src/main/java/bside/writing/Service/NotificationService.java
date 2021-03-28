package bside.writing.Service;

import bside.writing.Repository.NotificationRepository;
import bside.writing.domain.notification.Notification;
import bside.writing.dto.NotificationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class NotificationService {
    private final NotificationRepository notificationRepository;

    @Transactional
    public NotificationDto saveNotification(NotificationDto notificationDto){
        Notification entity = notificationRepository.save(notificationDto.toEntity());
        return new NotificationDto(entity);
    }

    @Transactional
    public NotificationDto getNotification(Long memberId){
        notificationRepository.findById(memberId);
        return null;
    }
}
