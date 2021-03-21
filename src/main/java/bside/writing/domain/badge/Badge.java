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
public class Badge implements Comparable<Badge> {

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

    @Override
    public int compareTo(Badge o) {
        if(!badgeCode.equals(o.badgeCode)) return badgeCode.compareTo(o.badgeCode);
        return badgeValue.compareTo(o.badgeValue);
    }
}
