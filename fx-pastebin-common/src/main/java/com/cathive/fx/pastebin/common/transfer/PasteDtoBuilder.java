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

package com.cathive.fx.pastebin.common.transfer;

import com.cathive.fx.pastebin.common.model.Paste;
import com.cathive.fx.pastebin.common.model.UserProfile;
import javafx.util.Builder;

import javax.json.JsonObject;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Builder for {@link com.cathive.fx.pastebin.common.transfer.PasteDto} instances.
 */
public class PasteDtoBuilder implements Builder<PasteDto> {

    private static final ObjectFactory OBJECT_FACTORY = new ObjectFactory();

    private Long id;
    private String title;
    private String content;
    private Long userProfileId;
    private Long pasteTypeId;
    private LocalDateTime created;


    public static PasteDtoBuilder create() {
        return new PasteDtoBuilder();
    }

    public static PasteDtoBuilder create(final Paste entity) {
        if (entity == null) { return null; }
        final PasteDtoBuilder builder = new PasteDtoBuilder()
                .id(entity.getId())
                .title(entity.getTitle())
                .content(entity.getContent())
                .pasteTypeId(entity.getPasteType().getId())
                .created(entity.getCreated());
        final UserProfile userProfile = entity.getUserProfile();
        if (userProfile != null) {
            builder.userProfileId(userProfile.getId());
        }
        return builder;
    }

    public static PasteDtoBuilder create(final JsonObject json) {
        if (json == null) { return null; }
        final PasteDtoBuilder builder = new PasteDtoBuilder();

        builder.id(json.getJsonNumber("id").longValue());
        builder.title(json.getString("title"));
        builder.content(json.getString("content"));
        builder.userProfileId(json.getJsonNumber("user").longValue());
        builder.pasteTypeId(json.getJsonNumber("pasteType").longValue());
        builder.created(LocalDateTime.parse(json.getString("created"), DateTimeFormatter.ISO_DATE_TIME));
        return builder;
    }


    public PasteDtoBuilder id(final Long id) {
        this.id = id;
        return this;
    }

    public PasteDtoBuilder title(final String title) {
        this.title = title;
        return this;
    }
    public PasteDtoBuilder content(final String content) {
        this.content = content;
        return this;
    }

    public PasteDtoBuilder pasteTypeId(final Long contentTypeId) {
        this.pasteTypeId = contentTypeId;
        return this;
    }

    public PasteDtoBuilder userProfileId(final Long userProfileId) {
        this.userProfileId = userProfileId;
        return this;
    }

    public PasteDtoBuilder created(final LocalDateTime created) {
        this.created = created;
        return this;
    }


    @Override
    public PasteDto build() {
        final PasteDto dto = OBJECT_FACTORY.createPasteDto();
        dto.setId(this.id);
        dto.setTitle(this.title);
        dto.setContent(this.content);
        dto.setPasteTypeId(this.pasteTypeId);
        dto.setUserProfileId(this.userProfileId);
        dto.setCreated(this.created);
        return dto;
    }

}
