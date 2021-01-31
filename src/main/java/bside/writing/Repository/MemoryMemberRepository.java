package bside.writing.Repository;

import bside.writing.Member.Member;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;


public class MemoryMemberRepository implements MemberRepository{

    private ConcurrentHashMap<Long, Member> store = new ConcurrentHashMap<>();
    private static long sequence = 0L;


    @Override
    public Member save(Member member) {
        member.setUserNumber(++sequence);
        store.put(member.getUserNumber(), member);
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
    public Optional<Member> findByUserName(String userName) {
        return store.values().stream()
                .filter(member -> member.getName().equals(userName))
                .findAny();
    }

    public void clear(){
        store.clear();
    }
}
