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

import javax.ejb.Local;
import java.util.Collection;

/**
 * Service for all Entities
 *
 * @author Alexander Erben
 */
@Local
public interface PastebinService {

    /*
    * {@link com.cathive.fx.pastebin.common.model.Paste} methods
    */
    Collection<Paste> findAllPastes();

    Collection<Paste> findPastesByUser(Long userId);

    Paste findPasteById(Long id);

    Collection<Paste> findPasteByType(Long typeId);

    Paste savePaste(Paste paste, Long userId, Long typeId);

    /*
    * {@link com.cathive.fx.pastebin.common.model.UserProfile} methods
    */
    Collection<UserProfile> findAllUserProfiles();

    UserProfile findUserProfileById(Long id);

    void deletePaste(Paste toDelete);

    UserProfile saveUserProfile(UserProfile toSave);

    void deleteUserProfile(UserProfile toDelete);

    /*
    * {@link com.cathive.fx.pastebin.common.model.PasteType} methods
    */
    Collection<PasteType> findAllPasteTypes();

    PasteType findPasteTypeById(Long id);

    PasteType savePasteType(PasteType toSave);

    void deletePasteType(PasteType toDelete);

}
