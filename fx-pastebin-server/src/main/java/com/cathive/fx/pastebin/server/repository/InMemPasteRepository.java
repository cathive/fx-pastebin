package com.cathive.fx.pastebin.server.repository;

import com.cathive.fx.pastebin.common.model.Paste;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Alexander Erben
 */
public class InMemPasteRepository implements PasteRepository {

    Map<Long, Paste> pastes = new HashMap<>();

    @Override
    public Collection<Paste> findAll() {
        return Collections.unmodifiableCollection(pastes.values());
    }

    @Override
    public Paste findById(Long id) {
        return pastes.get(id);
    }

    @Override
    public Paste save(Paste toSave) {
        if (toSave == null || toSave.getId() == null || toSave.getId() < 0)
            throw new IllegalArgumentException("Paste must not be null, paste id must be positive and non-null");
        return pastes.put(toSave.getId(), toSave);
    }

    @Override
    public void delete(Paste toDelete) {
        if (toDelete == null || toDelete.getId() == null || toDelete.getId() < 0)
            throw new IllegalArgumentException("Paste must not be null, paste id must be positive and non-null");
        pastes.remove(toDelete.getId());
    }

    public void clear() {
        pastes.clear();
    }
}
