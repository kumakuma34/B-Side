package bside.writing.domain.badge;

import lombok.*;

import javax.persistence.*;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "badge")
@Getter
@Entity
public class Badge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "badge_id")
    private Long badgeId;

    @Column(name = "member_id", nullable = false)
    private Long memberId;

    @Column(name = "badge_code", nullable = false)
    private String badgeCode;

    @Column(name = "badge_value", nullable = false)
    private String badgeValue;
}
