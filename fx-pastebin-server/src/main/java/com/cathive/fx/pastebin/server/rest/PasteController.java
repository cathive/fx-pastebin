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

package com.cathive.fx.pastebin.server.rest;

import com.cathive.fx.pastebin.common.model.Paste;
import com.cathive.fx.pastebin.server.server.PastebinService;

import javax.inject.Inject;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Collection;

import static java.time.format.DateTimeFormatter.ISO_DATE_TIME;
import static javax.json.Json.createArrayBuilder;
import static javax.json.Json.createObjectBuilder;

/**
 * Provides an external REST interface for {@link com.cathive.fx.pastebin.common.model.Paste} instances.
 * @author Alexander Erben
 * @author Benjamin P. Jung
 */
@Path("/pastes")
@Produces(MediaType.APPLICATION_JSON)
public class PasteController {

    @Inject
    private PastebinService pastebinService;

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getAllPastes() {
        Collection<Paste> allPastes = this.pastebinService.findAllPastes();
        JsonArrayBuilder returnObject = createArrayBuilder();
        allPastes.stream().map(this::buildPasteJson).forEach(returnObject::add);
        return returnObject.build().toString();
    }

    @GET
    @Path("/id/{id:\\d+}")
    public String getPasteById(@PathParam("id") final Long id) {
        return buildPasteJson(this.pastebinService.findPasteById(id)).toString();
    }

    @GET
    @Path("/user/{user:\\d+}")
    public String getPastesByUserProfile(@PathParam("user") final Long id) {
        Collection<Paste> allPastes = this.pastebinService.findPasteByUser(id);
        JsonArrayBuilder returnObject = createArrayBuilder();
        allPastes.stream().map(this::buildPasteJson).forEach(returnObject::add);
        return returnObject.build().toString();
    }

    @GET
    @Path("/type/{type:\\d+}")
    public String getPasteByPasteType(@PathParam("type") final Long id) {
        Collection<Paste> allPastes = this.pastebinService.findPasteByUser(id);
        JsonArrayBuilder returnObject = createArrayBuilder();
        allPastes.stream().map(this::buildPasteJson).forEach(returnObject::add);
        return returnObject.build().toString();

    }

    private JsonObject buildPasteJson(final Paste p) {
        JsonObjectBuilder singlePaste = createObjectBuilder();
        singlePaste.add("id", p.getId());
        singlePaste.add("title", p.getTitle());
        singlePaste.add("content", p.getContent());
        singlePaste.add("creation", p.getCreation().format(ISO_DATE_TIME));
        singlePaste.add("user", buildUserForPaste(p));
        singlePaste.add("pasteType", buildPasteTypeForPaste(p));
        return singlePaste.build();
    }

    private JsonObject buildPasteTypeForPaste(final Paste p) {
        return createObjectBuilder()
                .add("id", p.getPasteType().getId())
                .add("name", p.getPasteType().getName())
                .build();
    }

    private JsonObject buildUserForPaste(final Paste p) {
        JsonObjectBuilder userBuilder = createObjectBuilder();
        userBuilder.add("id", p.getUserProfile().getId());
        userBuilder.add("name", p.getUserProfile().getName());
        return userBuilder.build();
    }


}
