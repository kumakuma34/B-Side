package bside.writing.domain.member;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberTokenRespository extends JpaRepository<MemberToken, Long> {
}
