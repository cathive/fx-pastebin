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

package com.cathive.fx.pastebin.server.repository.jpa;

import com.cathive.fx.pastebin.common.model.Paste;
import com.cathive.fx.pastebin.server.repository.PasteRepository;

import javax.inject.Singleton;
import java.util.Collection;

/**
 * JPA Repository for {@link com.cathive.fx.pastebin.common.model.Paste}
 *
 * @author Alexander Erben
 */
@Singleton
public class JpaPasteRepository extends JpaRepository<Paste, Long> implements PasteRepository {

    @Override
    public Collection<Paste> findByUser(Long userId) {
        return em.createNamedQuery("paste.findByUser", Paste.class)
                .setParameter("id", userId)
                .getResultList();
    }

    @Override
    public Collection<Paste> findByType(String typeId) {
        return em.createNamedQuery("paste.findByType", Paste.class)
                .setParameter("id", typeId)
                .getResultList();
    }
}
