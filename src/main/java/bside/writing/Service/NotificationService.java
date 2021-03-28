package bside.writing.Service;

import bside.writing.Repository.NotificationRepository;
import bside.writing.domain.notification.Notification;
import bside.writing.dto.NotificationDto;
import bside.writing.templateClass.Entityable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class NotificationService {
    private final NotificationRepository notificationRepository;

    @Transactional
    public <T extends Entityable> NotificationDto save(T notificationDto){
        Notification entity = notificationRepository.save(notificationDto.toEntity());
        return new NotificationDto(entity);
    }

    @Transactional
    public List<NotificationDto> getNotification(Long memberId) {
        Optional<List<Notification>> entityList = notificationRepository.findByMemberId(memberId);
        if(entityList.isPresent()){
            List<NotificationDto> collect = entityList.get().stream()
                    .map((entity) -> new NotificationDto(entity)).collect(Collectors.toList());
            return deleteReservedNotification(collect);
        }
        return null;
    }

    public List<NotificationDto> deleteReservedNotification(List<NotificationDto> notificationDtoList){
        return  notificationDtoList.stream()
                .filter(e-> e.getReserveDate() == null || LocalDateTime.now().isBefore(e.getReserveDate()))
                .collect(Collectors.toList());
    }
}
