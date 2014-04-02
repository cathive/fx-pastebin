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

package com.cathive.fx.pastebin.common.model;

import javax.persistence.*;
import java.util.Collection;

/**
 * Encapsulates a user's profile
 *
 * @author Alexander Erben
 */
@Entity
@Table(name = "user_profile")
public class UserProfile {

    private String name;

    private Collection<Paste> pastes;

    private Long id;

    @Id
    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    @Column
    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "pasteType")
    public Collection<Paste> getPastes() {
        return this.pastes;
    }

    public void setPastes(final Collection<Paste> pastes) {
        this.pastes = pastes;
    }

}
