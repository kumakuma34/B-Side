package bside.writing.Service;

import bside.writing.Repository.ChallengeRepository;
import bside.writing.Repository.MemberRepository;
import bside.writing.Repository.NotificationRepository;
import bside.writing.domain.notification.Notification;
import bside.writing.dto.NotificationDto;
import bside.writing.enums.NotiType;
import bside.writing.templateClass.Entityable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class NotificationService {
    private final NotificationRepository notificationRepository;
    private final MemberRepository memberRepository;
    private final ChallengeRepository challengeRepository;

    @Transactional
    public <T extends Entityable> NotificationDto save(T notificationDto){
        Notification entity = notificationRepository.save(notificationDto.toEntity());
        return new NotificationDto(entity);
    }

    @Transactional
    public List<NotificationDto> getNotification(Long memberId){
        List<Notification> notiList = notificationRepository.findByMemberId(memberId)
                .orElseThrow(()-> new NoSuchElementException("no notification"));
        return IdtoName(toNotiDtoList(notiList));
    }

    public List<NotificationDto> toNotiDtoList(List<Notification> notificationList){
        return notificationList.stream()
                    .map((entity) -> new NotificationDto(entity))
                .collect(Collectors.toList());
    }

    public List<NotificationDto> IdtoName(List<NotificationDto> notificationDtoList){
        return notificationDtoList.stream()
                .map((dto) -> {
                    if (dto.getNotiType().getFromType().equals(NotiType.ARTICLE_LIKE.getFromType())){
                        dto.setFromName("article name");
                    }
                    else if(dto.getNotiType().getFromType().equals(NotiType.BEFORE_START_CHALLENGE.getFromType())){
                        dto.setFromName(challengeRepository.findById(dto.getFromId()).get().getChallengeTitle());
                    }
                    return dto;
                }).collect(Collectors.toList());
    }
}
