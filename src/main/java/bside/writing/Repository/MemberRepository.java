package bside.writing.Repository;

import bside.writing.Member.Member;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class MemberRepository implements Repository{

    private ConcurrentHashMap<Long, Member> store = new ConcurrentHashMap<>();
    private static long sequence = 0L;

    @Override
    public boolean save(Object obj) {
        Member member = (Member)obj;
        member.setUserNumber(++sequence);
        try{
            store.put(member.getUserNumber(), member);
        }
        catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    public Optional<Object> findByUserNumber(Long userNumber) {
        return Optional.ofNullable(store.get(userNumber));
    }
}
