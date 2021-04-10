package bside.writing.Repository;

import bside.writing.domain.challenge.ChallengeMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ChallengeMemberRepository extends JpaRepository<ChallengeMember,Long> {
    @Query("select c.memberId from ChallengeMember c WHERE c.challengeId = ?1")
    List<Long> findAllByChallengeId(Long challenge_id);

    @Query("select c.challengeMemberId from ChallengeMember c WHERE c.challengeId = ?1 AND c.memberId = ?2")
    List<Long> findDuplicate(Long challenge_id , Long member_id);
}
