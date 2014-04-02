package com.cathive.fx.pastebin.server.repository;

import com.cathive.fx.pastebin.common.model.Paste;

import java.util.Collection;

/**
 * @author Alexander Erben
 */
public interface PasteRepository {

    Collection<Paste> findAll();

    Paste findById(Long id);

    Paste save(Paste toSave);

    void delete(Paste toDelete);
}
