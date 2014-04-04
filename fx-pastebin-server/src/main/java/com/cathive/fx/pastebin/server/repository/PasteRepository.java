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

package com.cathive.fx.pastebin.server.repository;

import com.cathive.fx.pastebin.common.model.Paste;

import javax.ejb.Local;
import java.util.Collection;

/**
 * Repository for {@link com.cathive.fx.pastebin.common.model.Paste}
 *
 * @author Alexander Erben
 */
@Local
public interface PasteRepository extends Repository<Paste, Long> {

    /**
     * Find a {@link com.cathive.fx.pastebin.common.model.Paste} by {@link com.cathive.fx.pastebin.common.model.UserProfile}
     *
     * @param userId the user id
     * @return the associated entities, or empty if none found.
     */
    Collection<Paste> findByUser(Long userId);

    /**
     * Find a {@link com.cathive.fx.pastebin.common.model.Paste} by {@link com.cathive.fx.pastebin.common.model.PasteType}
     *
     * @param typeId the type id
     * @return the associated entities, or empty if none found.
     */
    Collection<Paste> findByType(String typeId);
}
