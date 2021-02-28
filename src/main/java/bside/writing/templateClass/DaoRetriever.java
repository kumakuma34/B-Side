package bside.writing.templateClass;

import javax.persistence.NoResultException;

@FunctionalInterface
public interface DaoRetriever<T> {
    T retrieve() throws NoResultException;
}