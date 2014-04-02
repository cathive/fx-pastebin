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

import javax.inject.Singleton;
import java.util.Collection;

/**
 * @author Alexander Erben
 */
@Singleton
public class DefaultPasteRepository extends AbstractRepository<Paste, Long> implements PasteRepository {

    @Override
    public Collection<Paste> findByUser(Long userId) {
        return em.createNamedQuery("findByUser", Paste.class).setParameter("id", userId).getResultList();
    }

    @Override
    public Collection<Paste> findByType(Long typeId) {
        return em.createNamedQuery("findByType", Paste.class).setParameter("id", typeId).getResultList();
    }
}
