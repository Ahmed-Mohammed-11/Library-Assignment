package com.books.library.daos.repositories;

import java.util.Optional;

public interface CrudRepository<T, ID> {
    ID insert(T entity);

    Optional<T> findById(ID id);

    Iterable<T> findAll();

    void update(T entity);

    void deleteById(ID id);
}
