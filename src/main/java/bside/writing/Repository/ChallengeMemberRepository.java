package bside.writing.Repository;

import bside.writing.domain.challenge.ChallengeMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChallengeMemberRepository extends JpaRepository<ChallengeMember,Long> {
}
