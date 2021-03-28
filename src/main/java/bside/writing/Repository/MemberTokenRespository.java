package bside.writing.Repository;

import bside.writing.domain.member.Member;
import bside.writing.domain.member.MemberToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberTokenRespository extends JpaRepository<MemberToken, Long> {
    Optional<MemberToken> findByRefreshToken(final String refreshToken);
}
