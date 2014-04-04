/*
 * Copyright (C) 2014 The Cat Hive Developers.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.cathive.fx.pastebin.server.repository;

import java.io.Serializable;
import java.util.Collection;

/**
 * {@code CRUD} repository.
 *
 * @param <T>  The domain type the repository manages.
 * @param <ID> The type of the id of the entity the repository manages.
 * @author Benjamin P. Jung
 */
public interface Repository<T, ID extends Serializable> extends Serializable {

    /**
     * Count result all entities.
     *
     * @return the count of all entities.
     */
    long count();

    /**
     * Delete entities.
     *
     * @param entities to delete, never <code>null</code> and must yield at least one entity.
     */
    void delete(Iterable<? extends T> entities);

    /**
     * Delete a single entity.
     *
     * @param entity to delete, never <code>null</code>
     */
    void delete(T entity);

    /**
     * Find all entities.
     *
     * @return all entities, or empty if none present.
     */
    Collection<T> findAll();

    /**
     * Find entities from first result with max bounds.
     *
     * @param firstResult the first ID to return.
     * @param maxResults  maximum number of entities to return.
     * @return the entities, empty if none found.
     */
    Collection<T> findAll(int firstResult, int maxResults);

    /**
     * Find by ID.
     *
     * @param id the id to search for.
     * @return the entity, <code>null</code> if none matches.
     */
    T findOne(ID id);

    /**
     * Flush state of repository.
     */
    void flush();

    /**
     * Save entities, update if already present.
     *
     * @param entities to save. Never <code>null</code> and must yield at least one entry.
     * @return the saved entities.
     */
    Iterable<T> save(Iterable<T> entities);

    /**
     * Save a single entity, update if already present.
     *
     * @param entity to save, never <code>null</code>
     * @return the saved entity
     */
    T save(T entity);

}

