package bside.writing.Repository;

import javax.persistence.NoResultException;

public interface DaoRetriever<T> {
    T retrieve() throws NoResultException;
}