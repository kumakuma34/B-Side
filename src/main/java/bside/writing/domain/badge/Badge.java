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
public class Badge{


    @Id
    @Column(name = "badge_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long badgeId;

    @Column(name = "member_id", nullable = false)
    private Long memberId;

    @Column(name = "badge_code", nullable = false)
    private String badgeCode;

    @Column(name = "badge_value", nullable = false)
    private String badgeValue;

}
