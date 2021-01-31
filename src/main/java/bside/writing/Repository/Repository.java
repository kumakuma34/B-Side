package bside.writing.Repository;

import java.util.Optional;

public interface Repository {

    boolean save(Object object);
    Optional<Object> findByUserNumber(Long userNumber);
}
