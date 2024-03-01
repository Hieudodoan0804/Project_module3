package com.ra.repository;

import java.util.List;

public interface Repository<T,K> {
    List<T> findAll(Class<T> entityClass);
    T findId(K id,Class<T> entityClass);
    T findName(K name,Class<T> entityClass);
    T add(T entity);
    T edit (T entity);
    boolean remove(K id,Class<T> entityClass);
}
