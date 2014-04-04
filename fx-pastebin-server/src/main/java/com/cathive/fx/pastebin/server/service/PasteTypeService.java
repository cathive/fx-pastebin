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

import com.cathive.fx.pastebin.common.model.PasteType;

import java.util.Collection;

/**
 * @author Alexander Erben
 */
public interface PasteTypeService {

    Collection<PasteType> findAllPasteTypes();

    PasteType findPasteTypeById(String id);

    PasteType savePasteType(PasteType toSave);

    void deletePasteType(PasteType toDelete);
}