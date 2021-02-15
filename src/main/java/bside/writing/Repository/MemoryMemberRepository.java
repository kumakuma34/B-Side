package bside.writing.Repository;

import bside.writing.Member.Member;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;


public class MemoryMemberRepository implements MemberRepository{

    private ConcurrentHashMap<Long, Member> store = new ConcurrentHashMap<>();
    private static long sequence = 0L;


    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> delete(Long userNumber) {
        Optional<Member> deletedMember = Optional.ofNullable(store.get(userNumber));
        store.remove(userNumber);
        return deletedMember;
    }

    @Override
    public Optional<Member> findByUserNumber(Long userNumber) {
        return Optional.ofNullable(store.get(userNumber));
    }

    @Override
    public List<Member> findAll() {
        return null;
    }

    @Override
    public Optional<List<Member>> findByUserName(String userName) {
      /*  return store.values().stream()
                .filter(member -> member.getName().equals(userName))
                .findAny();*/
        Optional<List<Member>> result = null;
        return result;
    }

    @Override
    public Optional<List<Member>> findByUserEmail(String userEmail) {
        return Optional.empty();
    }
    //interface에 method추가해서 일단 깡통만 구현해놓음
    //MemoryArticleRepository이거 날려도되지않나..?
    public void clear(){
        store.clear();
    }
}
