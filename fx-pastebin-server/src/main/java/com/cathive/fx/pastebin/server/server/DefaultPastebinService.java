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

package com.cathive.fx.pastebin.server.server;

import com.cathive.fx.pastebin.common.model.Paste;
import com.cathive.fx.pastebin.server.repository.PasteRepository;

import javax.ejb.Singleton;
import javax.inject.Inject;
import java.util.Collection;

/**
 * @author Alexander Erben
 */
@Singleton
public class DefaultPastebinService implements PastebinService{

    @Inject
    private PasteRepository pasteRepository;

    @Override
    public Collection<Paste> findAllPastes() {
        return pasteRepository.findAll();
    }

    @Override
    public Collection<Paste> findPasteByUser(Long userId) {
        return pasteRepository.findByUser(userId);
    }

    @Override
    public Paste findPasteById(Long id) {
        return pasteRepository.findOne(id);
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
