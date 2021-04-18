package bside.writing.Service;

import bside.writing.Repository.ArticleRepository;
import bside.writing.Repository.ChallengeRepository;
import bside.writing.Repository.NotificationRepository;
import bside.writing.domain.notification.Notification;
import bside.writing.dto.NotificationResponseDto;
import bside.writing.dto.NotificationSaveDto;
import bside.writing.enums.NotiTargetType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class NotificationService {
    private final NotificationRepository notificationRepository;
    private final ChallengeRepository challengeRepository;
    private final ArticleRepository articleRepository;

    @Transactional
    public void save(NotificationSaveDto saveDto){
        notificationRepository.save(saveDto.toEntity());
    }

    public List<NotificationResponseDto> getNotificationAsResponseDto(Long memberId){
        return getFromName(toResponseDto(getNotification(memberId)));
    }

    @Transactional
    public List<Notification> getNotification(Long memberId){
        return notificationRepository.findByMemberId(memberId)
                .orElseThrow(()-> new NoSuchElementException("no notification"));
    }

    public List<NotificationResponseDto> toResponseDto(List<Notification> notificationList){
        return notificationList.stream()
                    .map((entity) -> new NotificationResponseDto(entity))
                .collect(Collectors.toList());
    }

    public List<NotificationResponseDto> getFromName(List<NotificationResponseDto> responseDtoList){
        return responseDtoList.stream()
                .map((dto) -> {
                    NotiTargetType notiTargetType = dto.getNotiType().getTargetType();
                    if(!notiTargetType.equals(NotiTargetType.NA)){
                        Map<String, Object> target = dto.getTarget();
                        String fromName = "";
                        if (notiTargetType.equals(NotiTargetType.ARTICLE)){
                            fromName = articleRepository.findById(dto.getFromId()).get().getArticleTitle();
                        }
                        else if(notiTargetType.equals(NotiTargetType.CHALLENGE)){
                            fromName = challengeRepository.findById(dto.getFromId()).get().getChallengeTitle();
                        }
                        target.put("name", fromName);
                        dto.setNotiMessage("'" + fromName + "'" + dto.getNotiMessage());
                    }
                    return dto;
                }).collect(Collectors.toList());
    }
}
