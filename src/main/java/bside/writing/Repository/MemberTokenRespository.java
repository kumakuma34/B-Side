package bside.writing.Repository;

import bside.writing.domain.member.MemberToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberTokenRespository extends JpaRepository<MemberToken, Long> {
}
