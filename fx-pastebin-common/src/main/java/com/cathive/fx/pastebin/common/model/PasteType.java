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
import java.io.Serializable;
import java.util.Collection;

/**
 * The type of a paste, e.g. "XML", "JSON", "Java"...
 *
 * @author Alexander Erben
 */
@Entity
@Table(name = "paste_type")
public class PasteType implements Serializable {

    private static final long serialVersionUID = 1L;

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

    @Override
    public String toString() {
        return "PasteType{" +
                "name='" + name + '\'' +
                ", pastes=" + pastes +
                ", id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PasteType pasteType = (PasteType) o;

        if (id != null ? !id.equals(pasteType.id) : pasteType.id != null) return false;
        if (name != null ? !name.equals(pasteType.name) : pasteType.name != null) return false;
        if (pastes != null ? !pastes.equals(pasteType.pastes) : pasteType.pastes != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (pastes != null ? pastes.hashCode() : 0);
        result = 31 * result + (id != null ? id.hashCode() : 0);
        return result;
    }
}
