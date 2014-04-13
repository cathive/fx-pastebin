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

import com.cathive.fx.pastebin.common.model.PasteType;
import com.cathive.fx.pastebin.server.repository.PasteTypeRepository;

import javax.inject.Singleton;

/**
 * JPA Repository for {@link com.cathive.fx.pastebin.common.model.PasteType}
 *
 * @author Alexander Erben
 */
@Singleton
class JpaPasteTypeRepository extends JpaRepository<PasteType, Long> implements PasteTypeRepository {

    @Override
    public PasteType findOneByName(final String name) {
        return this.em.createNamedQuery("pasteType.findByName", PasteType.class)
                .setParameter("name", name)
                .getSingleResult();
    }

}
