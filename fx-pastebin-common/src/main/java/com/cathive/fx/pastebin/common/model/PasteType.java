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

import javafx.beans.NamedArg;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.Collection;

/**
 * The type of a paste, e.g. "XML", "JSON", "Java"...
 *
 * @author Alexander Erben
 */
@Entity
@Table(name = "paste_type")
@XmlRootElement
public class PasteType implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;

    private String description;

    private Collection<Paste> pastes;

    public PasteType() {
    }

    public PasteType(@NamedArg("name") final String name) {
        this.setName(name);
    }

    public PasteType(@NamedArg("name") final String name, @NamedArg("description") final String description) {
        this.name = name;
        this.description = description;
    }

    @Column
    @XmlElement
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Id
    @XmlElement
    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    @XmlTransient
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
                ", description='" + description + '\'' +
                ", pastes=" + pastes +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PasteType pasteType = (PasteType) o;

        if (description != null ? !description.equals(pasteType.description) : pasteType.description != null)
            return false;
        if (name != null ? !name.equals(pasteType.name) : pasteType.name != null) return false;
        if (pastes != null ? !pastes.equals(pasteType.pastes) : pasteType.pastes != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (pastes != null ? pastes.hashCode() : 0);
        return result;
    }
}
