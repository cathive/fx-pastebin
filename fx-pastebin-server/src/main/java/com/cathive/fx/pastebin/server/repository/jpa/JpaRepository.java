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

package com.cathive.fx.pastebin.server.repository.jpa;

import com.cathive.fx.pastebin.server.repository.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * An abstract repository bean that implements the most basic CRUD operations for a given
 * entity type.
 * <p>Basically a rip-off of what Spring did with their CrudRepository class.</p>
 *
 * @author Benjamin P. Jung
 */
@SuppressWarnings("unchecked")
abstract class JpaRepository<T, ID extends Serializable> implements Repository<T, ID> {

    @PersistenceContext(unitName = "fx-pastebin")
    EntityManager em;

    @Override
    public long count() {
        final CriteriaBuilder cb = em.getCriteriaBuilder();
        final CriteriaQuery<Long> q = cb.createQuery(Long.class);
        q.select(cb.count(q.from(this.getResultClass())));
        return em.createQuery(q).getSingleResult();
    }

    @Override
    public Collection<T> findAll() {
        final CriteriaBuilder cb = em.getCriteriaBuilder();
        final CriteriaQuery<T> q = cb.createQuery(this.getResultClass());
        final Root<T> root = q.from(this.getResultClass());
        q.select(root);
        q.orderBy(cb.asc(root.get(root.getModel().getDeclaredId(this.getIdClass()))));
        List<T> resultList = em.createQuery(q).getResultList();
        return resultList == null ? Collections.emptyList() : resultList;
    }

    @Override
    public Collection<T> findAll(final int firstResult, final int maxResults) {
        final CriteriaBuilder cb = em.getCriteriaBuilder();
        final CriteriaQuery<T> q = cb.createQuery(this.getResultClass());

        final Root<T> root = q.from(this.getResultClass());

        q.select(root);
        q.orderBy(cb.asc(root.get(root.getModel().getDeclaredId(this.getIdClass()))));

        List<T> resultList = em.createQuery(q)
                .setFirstResult(firstResult)
                .setMaxResults(maxResults)
                .getResultList();
        return resultList == null ? Collections.emptyList() : resultList;
    }

    @Override
    public T findOne(final ID id) {
        return em.find(this.getResultClass(), id);
    }

    @Override
    public T delete(final T entity) {
        final T _entity = em.merge(entity);
        em.remove(_entity);
        return _entity;
    }

    @Override
    public void delete(final Iterable<? extends T> entities) {
        entities.forEach(this::delete);
    }

    @Override
    public T save(final T entity) {
        final T _entity = em.merge(entity);
        em.persist(_entity);
        return _entity;
    }

    @Override
    public void flush() {
        em.flush();
    }

    @Override
    public Iterable<T> save(final Iterable<T> entities) {
        final Collection<T> ret = new ArrayList<>();
        entities.forEach((t) -> ret.add(save(t)));
        return ret;
    }

    Class<ID> getIdClass() {
        final ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
        return (Class<ID>) genericSuperclass.getActualTypeArguments()[1];
    }

    Class<T> getResultClass() {
        final ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
        return (Class<T>) genericSuperclass.getActualTypeArguments()[0];
    }

}
