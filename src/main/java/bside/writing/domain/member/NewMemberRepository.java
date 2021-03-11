package bside.writing.domain.member;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NewMemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByEmail(final String email);

}
