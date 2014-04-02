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
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDate;

/**
 * Encapsulates a single paste
 * @author Alexander Erben
 */
@Entity
@Table(name = "paste")
@XmlRootElement
public class Paste {

    private static final long serialVersionUID = 1L;

    private String title;

    private String content;

    private PasteType pasteType;

    private UserProfile userProfile;

    private Long id;

    private LocalDate creation;

    @Id
    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    @Column
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @ManyToOne
    public PasteType getPasteType() {
        return pasteType;
    }

    public void setPasteType(PasteType pasteType) {
        this.pasteType = pasteType;
    }

    @ManyToOne
    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }

    @Column
    public LocalDate getCreation() {
        return creation;
    }

    public void setCreation(LocalDate creation) {
        this.creation = creation;
    }

    @Override
    public String toString() {
        return "Paste{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", pasteType=" + pasteType +
                ", userProfile=" + userProfile +
                ", id=" + id +
                ", creation=" + creation +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Paste paste = (Paste) o;

        if (content != null ? !content.equals(paste.content) : paste.content != null) return false;
        if (id != null ? !id.equals(paste.id) : paste.id != null) return false;
        if (creation != null ? !creation.equals(paste.creation) : paste.creation != null) return false;
        if (pasteType != null ? !pasteType.equals(paste.pasteType) : paste.pasteType != null) return false;
        if (title != null ? !title.equals(paste.title) : paste.title != null) return false;
        if (userProfile != null ? !userProfile.equals(paste.userProfile) : paste.userProfile != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (pasteType != null ? pasteType.hashCode() : 0);
        result = 31 * result + (userProfile != null ? userProfile.hashCode() : 0);
        result = 31 * result + (id != null ? id.hashCode() : 0);
        result = 31 * result + (creation != null ? creation.hashCode() : 0);
        return result;
    }
}
