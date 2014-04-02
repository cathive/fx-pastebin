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

import com.cathive.fx.pastebin.common.model.Paste;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Alexander Erben
 */
public class InMemPasteRepository implements PasteRepository {

    private final Map<Long, Paste> pastes = new HashMap<>();

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
