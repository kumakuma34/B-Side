package bside.writing.Repository;

import java.util.Optional;

public interface Repository {

    boolean save();
    Optional<Object> findByUserNumber(Long userNumber);
}
