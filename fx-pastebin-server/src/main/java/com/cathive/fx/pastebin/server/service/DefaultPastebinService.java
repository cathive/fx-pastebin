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
 * @author Alexander Erben
 */
@Singleton
public class DefaultPastebinService implements PastebinService {

    @Inject
    private PasteRepository pasteRepository;

    @Inject
    private UserProfileRepository userProfileRepository;

    @Inject
    private PasteTypeRepository pasteTypeRepository;

    /*
     * {@link com.cathive.fx.pastebin.common.model.Paste} methods
     */

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
    public Collection<Paste> findPasteByType(Long typeId) {
        return pasteRepository.findByType(typeId);
    }

    @Override
    public Paste savePaste(Paste toSave) {
        return pasteRepository.save(toSave);
    }

    @Override
    public void deletePaste(Paste toDelete) {
        pasteRepository.delete(toDelete);
    }

    /*
     * {@link com.cathive.fx.pastebin.common.model.UserProfile} methods
     */

    @Override
    public Collection<UserProfile> findAllUserProfiles() {
        return userProfileRepository.findAll();
    }

    @Override
    public UserProfile findUserProfileById(Long id) {
        return userProfileRepository.findOne(id);
    }

    @Override
    public UserProfile saveUserProfile(UserProfile toSave) {
        return userProfileRepository.save(toSave);
    }

    @Override
    public void deleteUserProfile(UserProfile toDelete) {
        userProfileRepository.delete(toDelete);
    }


    /*
     * {@link com.cathive.fx.pastebin.common.model.PasteType} methods
     */

    @Override
    public Collection<PasteType> findAllPasteTypes() {
        return pasteTypeRepository.findAll();
    }

    @Override
    public PasteType findPasteTypeById(Long id) {
        return pasteTypeRepository.findOne(id);
    }

    @Override
    public PasteType savePasteType(PasteType toSave) {
        return pasteTypeRepository.save(toSave);
    }

    @Override
    public void deletePasteType(PasteType toDelete) {
        pasteTypeRepository.delete(toDelete);
    }

    @Override
    public void addPaste(Paste paste, Long userId, Long typeId) {
        UserProfile user = userProfileRepository.findOne(userId);
        if (user == null) throw new IllegalArgumentException("User with id "+userId+" not found");
        PasteType type = pasteTypeRepository.findOne(typeId);
        if (type == null) throw new IllegalArgumentException("Paste Type with id "+typeId+" not found");
        paste.setUserProfile(user);
        paste.setPasteType(type);
        pasteRepository.save(paste);
    }
}
