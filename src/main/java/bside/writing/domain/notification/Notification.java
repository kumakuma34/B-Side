package bside.writing.domain.notification;

import bside.writing.templateClass.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Table(name = "notification")
@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Notification extends BaseTimeEntity {
    @Id
    @Column(name = "noti_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "member_id", nullable = false)
    private Long memberId;

    @Column(name = "from_member_id")
    private Long fromMemberId;

    @Column(name = "from_article_id")
    private Long fromArticleId;

    @Column(name = "from_challenge_id")
    private Long fromChallengeId;

    @Column(name = "noti_type", nullable = false)
    private String notiType;

    @Column(name = "noti_message", nullable = false)
    private String notiMessage;

    @Column(name = "noti_url")
    private String notiUrl;

    @Column(name = "noti_read", nullable = false)
    private Boolean notiRead;

    @Column(name = "reserve_date")
    private LocalDateTime reserveDate;
}
