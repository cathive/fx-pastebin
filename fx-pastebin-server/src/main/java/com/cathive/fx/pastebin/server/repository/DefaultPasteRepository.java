package com.cathive.fx.pastebin.server.repository;

import com.cathive.fx.pastebin.common.model.Paste;

import javax.inject.Singleton;
import java.util.Collection;

/**
 * @author Alexander Erben
 */
@Singleton
public class DefaultPasteRepository extends AbstractRepository<Paste, Long> implements PasteRepository {

    @Override
    public Collection<Paste> findByUser(Long userId) {
        return em.createNamedQuery("findByUser", Paste.class).setParameter("id", userId).getResultList();
    }

    @Override
    public Collection<Paste> findByType(Long typeId) {
        return em.createNamedQuery("findByType", Paste.class).setParameter("id", typeId).getResultList();
    }
}
