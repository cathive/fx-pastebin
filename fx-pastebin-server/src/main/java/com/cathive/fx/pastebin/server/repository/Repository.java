/*
 * Copyright (C) 2014 The Cat Hive Developers.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
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

