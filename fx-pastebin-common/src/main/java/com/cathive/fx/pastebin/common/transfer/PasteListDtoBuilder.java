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

package com.cathive.fx.pastebin.common.transfer;

import com.cathive.fx.pastebin.common.model.Paste;
import javafx.util.Builder;

import javax.json.JsonArray;
import javax.json.JsonObject;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Builder for {@link com.cathive.fx.pastebin.common.transfer.PasteListDto} instances.
 * @author Benjamin P. Jung
 */
public class PasteListDtoBuilder implements Builder<PasteListDto> {

    private static final ObjectFactory OBJECT_FACTORY = new ObjectFactory();

    public static PasteListDtoBuilder create() {
        return new PasteListDtoBuilder();
    }

    public static PasteListDtoBuilder create(final Collection<Paste> entities) {
        if (entities == null) { return null; }
        final PasteListDtoBuilder builder = new PasteListDtoBuilder();
        final List<PasteDto> pastes = new ArrayList<>(entities.size());
        for (final Paste paste: entities) {
            pastes.add(PasteDtoBuilder.create(paste).build());
        }
        builder.pastes(pastes);
        return builder;
    }

    public static PasteListDtoBuilder create(final JsonArray json) {
        if (json == null) { return null; }
        final PasteListDtoBuilder builder = new PasteListDtoBuilder();
        final List<PasteDto> pastes = new ArrayList<>();
        json.forEach(jsonValue -> {
            JsonObject jsonObject = (JsonObject) jsonValue;
            pastes.add(PasteDtoBuilder.create(jsonObject).build());
        });
        builder.pastes(pastes);
        return builder;
    }


    private Collection<PasteDto> pastes;


    public PasteListDtoBuilder pastes(final Collection<PasteDto> pastes) {
        this.pastes = pastes;
        return this;
    }


    @Override
    public PasteListDto build() {
        final PasteListDto dto = OBJECT_FACTORY.createPasteListDto();
        if (this.pastes != null) { dto.getPaste().addAll(this.pastes); }
        dto.setSize(this.pastes == null ? Integer.valueOf(0) : Integer.valueOf(this.pastes.size()));
        return dto;
    }

}
