package bside.writing.Repository;

import bside.writing.domain.member.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {

    Member save(Member member);
    Optional<Member> delete(Member member);
    Optional<Member> findByUserNumber(Long userNumber);
    Optional<List<Member>> findByUserName(String userName);
    Optional<Member> findByUserEmail(String userEmail);
}
