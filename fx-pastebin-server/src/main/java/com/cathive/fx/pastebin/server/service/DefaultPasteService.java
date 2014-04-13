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

package com.cathive.fx.pastebin.server.service;

import com.cathive.fx.pastebin.common.model.Paste;
import com.cathive.fx.pastebin.common.model.PasteType;
import com.cathive.fx.pastebin.common.model.UserProfile;
import com.cathive.fx.pastebin.server.repository.PasteRepository;
import com.cathive.fx.pastebin.server.repository.PasteTypeRepository;
import com.cathive.fx.pastebin.server.repository.UserProfileRepository;

import javax.ejb.Singleton;
import javax.inject.Inject;
import java.util.Collection;

/**
 * Default service for all Entities
 *
 * @author Alexander Erben
 */
@Singleton
public class DefaultPasteService implements PasteService {

    @Inject
    private PasteRepository pasteRepository;

    @Inject
    private PasteTypeRepository pasteTypeRepository;

    @Inject
    private UserProfileRepository userProfileRepository;

    @Override
    public Collection<Paste> findAllPastes() {
        return pasteRepository.findAll();
    }

    @Override
    public Paste findPasteById(Long id) {
        return pasteRepository.findOne(id);
    }

    @Override
    public Collection<Paste> findPastesByUser(Long userId) {
        return pasteRepository.findByUser(userId);
    }

    @Override
    public Collection<Paste> findPasteByType(String typeId) {
        return pasteRepository.findByType(typeId);
    }

    @Override
    public Paste deletePaste(Long toDelete) {
        System.out.print("xxxxxxxxxxxxxxxxxxxxxxxx" + toDelete);
        return pasteRepository.delete(findPasteById(toDelete));
    }

    @Override
    public Paste savePaste(Paste paste, Long userId, String typeId) {
        UserProfile user = userProfileRepository.findOne(userId);
        if (user == null) throw new IllegalArgumentException("User with id " + userId + " not found");
        PasteType type = pasteTypeRepository.findOneByName(typeId);
        if (type == null) throw new IllegalArgumentException("Paste Type with id " + typeId + " not found");
        paste.setUserProfile(user);
        paste.setPasteType(type);
        return pasteRepository.save(paste);
    }
}
