package bside.writing.Repository;

import bside.writing.Member.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {

    Member save(Member member);
    Optional<Member> delete(Long idNumber);
    Optional<Member> findByUserNumber(Long userNumber);
    Optional<List<Member>> findByUserName(String userName);
    Optional<Member> findByUserEmail(String userEmail);
    List<Member> findAll();
}
