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
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Encapsulates a single paste
 *
 * @author Alexander Erben
 */
@Entity
@Table(name = "paste")
@NamedQueries({
        @NamedQuery(name = "paste.findByUser", query =
                "SELECT OBJECT(p) FROM Paste p WHERE :id = ANY (SELECT profile.id FROM p.userProfile profile)"),
        @NamedQuery(name = "paste.findByType", query =
                "SELECT OBJECT(p) FROM Paste p WHERE :id = ANY (SELECT type.id FROM p.pasteType type)")
})
public class Paste implements Serializable {

    private static final long serialVersionUID = 1L;

    private String title;

    private String content;

    private PasteType pasteType;

    private UserProfile userProfile;

    private Long id;

    private LocalDateTime created;

    public Paste() {
    }

    public Paste(@NamedArg("title") final String title,
                 @NamedArg("content") final String content,
                 @NamedArg("created") final LocalDateTime created) {
        this.title = title;
        this.content = content;
        this.created = created;
    }

    @Id
    @GeneratedValue
    @XmlElement
    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    @Column
    @XmlElement
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column
    @XmlElement
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @ManyToOne
    @XmlTransient
    public PasteType getPasteType() {
        return pasteType;
    }

    public void setPasteType(PasteType pasteType) {
        this.pasteType = pasteType;
    }

    @ManyToOne
    @XmlTransient
    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }

    @Column
    @XmlElement
    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime creation) {
        this.created = creation;
    }

    @XmlElement(name = "userProfile_id")
    public Long userProfileId() {
        return getUserProfile().getId();
    }

    @XmlElement(name = "pasteType_name")
    public String pasteType() {
        return getPasteType().getName();
    }

    @Override
    public String toString() {
        return "Paste{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", pasteType=" + pasteType +
                ", userProfile=" + userProfile +
                ", id=" + id +
                ", creation=" + created +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Paste paste = (Paste) o;

        return !(content != null ? !content.equals(paste.content) : paste.content != null)
                && !(id != null ? !id.equals(paste.id) : paste.id != null)
                && !(created != null ? !created.equals(paste.created) : paste.created != null)
                && !(pasteType != null ? !pasteType.equals(paste.pasteType) : paste.pasteType != null)
                && !(title != null ? !title.equals(paste.title) : paste.title != null)
                && !(userProfile != null ? !userProfile.equals(paste.userProfile) : paste.userProfile != null);
    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (pasteType != null ? pasteType.hashCode() : 0);
        result = 31 * result + (userProfile != null ? userProfile.hashCode() : 0);
        result = 31 * result + (id != null ? id.hashCode() : 0);
        result = 31 * result + (created != null ? created.hashCode() : 0);
        return result;
    }
}
