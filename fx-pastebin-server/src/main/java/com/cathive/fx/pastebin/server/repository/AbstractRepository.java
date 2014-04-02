package com.cathive.fx.pastebin.server.repository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * An abstract repository bean that implements the most basic CRUD operations for a given
 * entity type.
 * <p>Basically a rip-off of what Spring did with their CrudRepository class.</p>
 * @author Benjamin P. Jung
 */
abstract class AbstractRepository<T, ID extends Serializable> implements Repository<T, ID> {


    public AbstractRepository() {
        super();
    }

    public AbstractRepository(@NotNull final EntityManager entityManager) {
        this();
        this.setEntityManager(entityManager);
    }


    protected Class<ID> getIdClass() {
        final ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
        return (Class<ID>) genericSuperclass.getActualTypeArguments()[1];
    }

    protected Class<T> getResultClass() {
        final ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
        return (Class<T>) genericSuperclass.getActualTypeArguments()[0];
    }

    @NotNull
    public abstract EntityManager getEntityManager();

    public abstract void setEntityManager(@NotNull final EntityManager entityManager);

    @Override
    public long count() {

        final EntityManager em = this.getEntityManager();
        final CriteriaBuilder cb = em.getCriteriaBuilder();
        final CriteriaQuery<Long> q = cb.createQuery(Long.class);

        q.select(cb.count(q.from(this.getResultClass())));

        return em.createQuery(q).getSingleResult().longValue();

    }

    @Override
    public Collection<T> findAll() {

        final EntityManager em = this.getEntityManager();
        final CriteriaBuilder cb = em.getCriteriaBuilder();
        final CriteriaQuery<T> q = cb.createQuery(this.getResultClass());

        final Root<T> root = q.from(this.getResultClass());

        q.select(root);
        q.orderBy(cb.asc(root.get(root.getModel().getDeclaredId(this.getIdClass()))));

        return em.createQuery(q).getResultList();
    }

    @Override
    public Collection<T> findAll(final int firstResult, final int maxResults) {

        final EntityManager em = this.getEntityManager();
        final CriteriaBuilder cb = em.getCriteriaBuilder();
        final CriteriaQuery<T> q = cb.createQuery(this.getResultClass());

        final Root<T> root = q.from(this.getResultClass());

        q.select(root);
        q.orderBy(cb.asc(root.get(root.getModel().getDeclaredId(this.getIdClass()))));

        return em.createQuery(q)
                .setFirstResult(firstResult)
                .setMaxResults(maxResults)
                .getResultList();

    }

    @Override
    public T findOne(final ID id) {
        return this.getEntityManager().find(this.getResultClass(), id);
    }

    @Override
    public void delete(final T entity) {
        final EntityManager em = this.getEntityManager();
        final T _entity = em.merge(entity);
        em.remove(_entity);
    }

    @Override
    public void delete(final Iterable<? extends T> entities) {
        for (final T entity: entities) {
            delete(entity);
        }
    }

    @Override
    public T save(final T entity) {
        final EntityManager em = this.getEntityManager();
        // TODO Does merge() operate inline?!
        final T _entity = em.merge(entity);
        em.persist(_entity);
        return _entity;
    }

    @Override
    public Iterable<T> save(final Iterable<T> entities) {
        // TODO Does merge() operate inline?!
        final List<T> _entities = new ArrayList<>();
        for (final T entity: entities) {
            _entities.add(save(entity));

        }
        return _entities;
    }

}
