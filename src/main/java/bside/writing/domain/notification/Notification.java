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

    @Column(name = "noti_type", nullable = false)
    private String notiType;

    @Column(name = "noti_message", nullable = false)
    private String notiMessage;

    @Column(name = "noti_read", nullable = false)
    private Boolean notiRead;

    @Column(name = "noti_start_date")
    private LocalDateTime notiStartDate;

    @Column(name = "from_type")
    private String fromType;

    @Column(name = "from_id")
    private Long fromId;
}
