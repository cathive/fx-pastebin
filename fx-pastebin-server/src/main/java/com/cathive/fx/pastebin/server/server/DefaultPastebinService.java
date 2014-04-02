package com.cathive.fx.pastebin.server.server;

import com.cathive.fx.pastebin.common.model.Paste;
import com.cathive.fx.pastebin.server.repository.PasteRepository;

import java.util.Collection;

/**
 * @author Alexander Erben
 */
public class DefaultPastebinService implements PastebinService{

    private final PasteRepository pasteRepository;

    public DefaultPastebinService(PasteRepository pasteRepository) {
        this.pasteRepository = pasteRepository;
    }

    @Override
    public Collection<Paste> findAllPastes() {
        return pasteRepository.findAll();
    }

    @Override
    public Paste findPasteById(Long id) {
        return pasteRepository.findById(id);
    }

    @Override
    public Paste savePaste(Paste toSave) {
        return pasteRepository.save(toSave);
    }

    @Override
    public void deletePaste(Paste toDelete) {
        pasteRepository.delete(toDelete);
    }
}
