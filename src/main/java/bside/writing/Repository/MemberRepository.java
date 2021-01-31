package bside.writing.Repository;

import java.util.Optional;

public class MemberRepository implements Repository{

    @Override
    public boolean save(Object Member) {
        return false;
    }

    @Override
    public Optional<Object> findByUserNumber(Long userNumber) {
        return Optional.empty();
    }
}
