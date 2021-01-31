package bside.writing.Repository;

import java.util.Optional;

public interface Repository {

    boolean save(Object object);
    boolean delete(Long idNumber);
    Optional<Object> findByUserNumber(Long userNumber);
}
