package com.cathive.fx.pastebin.server.server;

import com.cathive.fx.pastebin.common.model.Paste;

import java.util.Collection;

/**
 * @author Alexander Erben
 */
public interface PastebinService {

    public Collection<Paste> findAllPastes();

    public Paste findPasteById(Long id);

    public Paste savePaste(Paste toSave);

    public void deletePaste(Paste toDelete);
}
