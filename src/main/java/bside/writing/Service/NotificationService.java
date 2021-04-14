package bside.writing.Service;

import bside.writing.Repository.ChallengeRepository;
import bside.writing.Repository.MemberRepository;
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
    private final MemberRepository memberRepository;
    private final ChallengeRepository challengeRepository;
    //private final

    @Transactional
    public <T extends Entityable> NotificationDto save(T notificationDto){
        Notification entity = notificationRepository.save(notificationDto.toEntity());
        return new NotificationDto(entity);
    }

    @Transactional
    public List<NotificationDto> getNotification(Long memberId) {
        List<Notification> notificationsList = notificationRepository.findByMemberId(memberId)
                .orElseThrow();
        return IdtoName(toNotiDtoList(notificationsList));
    }

    public List<NotificationDto> toNotiDtoList(List<Notification> notificationList){
        return notificationList.stream()
                    .map((entity) -> new NotificationDto(entity))
                .collect(Collectors.toList());
    }

    public List<NotificationDto> IdtoName(List<NotificationDto> notificationDtoList){
        return notificationDtoList.stream()
                .map((dto) -> {
                    if (dto.getFromArticleId() != null)
                        dto.setFromArticleName("article repo 미구현");
                    if (dto.getFromChallengeId() != null)
                        dto.setFromChallengeName(challengeRepository.findById(dto.getFromArticleId()).get().getChallengeTitle());
                    if (dto.getFromMemberId() != null)
                        dto.setFromMemberName(memberRepository.findById(dto.getFromMemberId()).get().getNickName());
                    return dto;
                }).collect(Collectors.toList());
    }

}
