package com.cathive.fx.pastebin.common;

import com.cathive.fx.pastebin.common.model.Paste;
import com.cathive.fx.pastebin.common.model.PasteType;
import com.cathive.fx.pastebin.common.model.UserProfile;

import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import java.util.Collection;

import static java.time.format.DateTimeFormatter.ISO_DATE_TIME;
import static javax.json.Json.createArrayBuilder;
import static javax.json.Json.createObjectBuilder;

/**
 * @author Alexander Erben (a_erben@outlook.com)
 */
public class JsonConverter {

    /**
     * Build a JSON representation of {@link com.cathive.fx.pastebin.common.model.Paste}
     * WITHOUT referenced entities
     *
     * @param p the paste
     * @return the JSON representation
     */
    public JsonObject buildPaste(final Paste p) {
        final JsonObjectBuilder singlePaste = createObjectBuilder();
        singlePaste.add("id", p.getId());
        singlePaste.add("title", p.getTitle());
        singlePaste.add("content", p.getContent());
        singlePaste.add("creation", p.getCreation().format(ISO_DATE_TIME));
        return singlePaste.build();
    }

    /**
     * Build a JSON representation of {@link com.cathive.fx.pastebin.common.model.PasteType}
     * WITHOUT referenced entities
     *
     * @param p the {@link com.cathive.fx.pastebin.common.model.PasteType}
     * @return the JSON representation
     */
    public JsonObject buildPasteType(final PasteType p) {
        return createObjectBuilder()
                .add("id", p.getId())
                .add("name", p.getName())
                .build();
    }

    /**
     * Build a JSON representation of {@link com.cathive.fx.pastebin.common.model.PasteType}
     * WITH referenced entities
     *
     * @param p                the {@link com.cathive.fx.pastebin.common.model.PasteType}
     * @param associatedPastes the pastes associated to this entity
     * @return the JSON representation
     */
    public JsonObject buildPasteTypeWithReferences(final PasteType p, Collection<Paste> associatedPastes) {
        final JsonArrayBuilder pastes = createArrayBuilder();
        associatedPastes.stream().map(this::buildPaste).forEach(pastes::add);
        return createObjectBuilder()
                .add("id", p.getId())
                .add("name", p.getName())
                .add("pastes", pastes)
                .build();
    }

    /**
     * Build a JSON representation of {@link com.cathive.fx.pastebin.common.model.Paste}
     * WITH referenced entities
     *
     * @param p         the paste
     * @param pasteType the paste type
     * @param user      the user profile
     * @return the JSON representation
     */
    public JsonObject buildPasteWithReferences(final Paste p, final UserProfile user, final PasteType pasteType) {
        final JsonObjectBuilder singlePaste = createObjectBuilder();
        singlePaste.add("id", p.getId());
        singlePaste.add("title", p.getTitle());
        singlePaste.add("content", p.getContent());
        singlePaste.add("creation", p.getCreation().format(ISO_DATE_TIME));
        singlePaste.add("user", buildUser(user));
        singlePaste.add("pasteType", buildPasteType(pasteType));
        return singlePaste.build();
    }

    /**
     * Build a JSON representation of {@link com.cathive.fx.pastebin.common.model.UserProfile}
     * WITHOUT referenced entities
     *
     * @param p the user profile
     * @return the JSON representation
     */
    public JsonObject buildUser(final UserProfile p) {
        final JsonObjectBuilder userBuilder = createObjectBuilder();
        userBuilder.add("id", p.getId());
        userBuilder.add("name", p.getName());
        return userBuilder.build();
    }
}
