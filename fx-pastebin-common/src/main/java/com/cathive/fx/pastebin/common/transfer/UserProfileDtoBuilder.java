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

import com.cathive.fx.pastebin.common.model.UserProfile;
import javafx.util.Builder;

/**
 * Builder for {@link com.cathive.fx.pastebin.common.transfer.UserProfileDto} instances.
 * @author Benjamin P. Jung
 */
public class UserProfileDtoBuilder implements Builder<UserProfileDto> {

    private Long id;
    private String name;


    public static UserProfileDtoBuilder create() {
        return new UserProfileDtoBuilder();
    }

    public static UserProfileDtoBuilder create(final UserProfile entity) {
        return entity == null ? null : new UserProfileDtoBuilder()
                .id(entity.getId())
                .name(entity.getName());
    }


    public UserProfileDtoBuilder id(final Long id) {
        this.id = id;
        return this;
    }

    public UserProfileDtoBuilder name(final String name) {
        this.name = name;
        return this;
    }


    @Override
    public UserProfileDto build() {
        final UserProfileDto dto = new UserProfileDto();
        dto.setId(this.id);
        dto.setName(this.name);
        return dto;
    }

}
