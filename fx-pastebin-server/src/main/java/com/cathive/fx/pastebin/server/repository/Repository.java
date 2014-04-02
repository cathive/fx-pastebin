package com.cathive.fx.pastebin.server.repository;

import java.io.Serializable;
import java.util.Collection;

/**
 * {@code CRUD} repository.
 * @param <T>
 *     The domain type the repository manages.
 * @param <ID>
 *     The type of the id of the entity the repository manages.
 * @author Benjamin P. Jung
 */
interface Repository<T, ID extends Serializable> extends Serializable {

    /**
     * Returns the number of entities available.
     * @return
     *     The number of entities.
     */
    long count();

    void delete(Iterable<? extends T> entities);

    void delete(T entity);

    Collection<T> findAll();

    Collection<T> findAll(int firstResult, int maxResults);

    T findOne(ID id);

    Iterable<T> save(Iterable<T> entities);

    T save(T entity);

}

