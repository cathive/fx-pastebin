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

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * The type of a paste, e.g. "XML", "JSON", "Java"...
 *
 * @author Alexander Erben
 */
@Entity
public class PasteType extends AbstractEntity {

    private String name;

    @Column
    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }
}