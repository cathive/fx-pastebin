/*
 * Copyright (C) 2014 The Cat Hive Developers.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * may obtain a copy of the License at
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

import com.cathive.fx.pastebin.common.JsonConverter;
import com.cathive.fx.pastebin.common.model.Paste;
import com.cathive.fx.pastebin.server.service.PastebinService;

import javax.inject.Inject;
import javax.inject.Named;
import javax.json.JsonArrayBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import static javax.json.Json.createArrayBuilder;

/**
 * Provides an external REST interface for {@link com.cathive.fx.pastebin.common.model.Paste} instances.
 *
 * @author Alexander Erben
 * @author Benjamin P. Jung
 */
@Path("/pastes")
@Produces(MediaType.APPLICATION_JSON)
public class PasteController {

    @Inject
    private PastebinService pastebinService;

    @Inject
    @Named("converter")
    private JsonConverter jsonConverter;

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getAllPastes() {
        final JsonArrayBuilder returnObject = createArrayBuilder();
        pastebinService.findAllPastes().stream().map(jsonConverter::buildPaste)
                .forEach(returnObject::add);
        return returnObject.build().toString();
    }

    @GET
    @Path("/id/{id:\\d+}")
    public String getPasteById(@PathParam("id") final Long id) {
        Paste paste = this.pastebinService.findPasteById(id);
        return jsonConverter.buildPaste(paste).toString();
    }

    @GET
    @Path("/user/{user:\\d+}")
    public String getPastesByUserProfile(@PathParam("user") final Long id) {
        final JsonArrayBuilder returnObject = createArrayBuilder();
        pastebinService.findPastesByUser(id).stream().map(
                jsonConverter::buildPaste)
                .forEach(returnObject::add);
        return returnObject.build().toString();
    }

    @GET
    @Path("/type/{type:\\d+}")
    public String getPasteByPasteType(@PathParam("type") final Long id) {
        final JsonArrayBuilder returnObject = createArrayBuilder();
        pastebinService.findPastesByUser(id).stream().map(
                jsonConverter::buildPaste)
                .forEach(returnObject::add);
        return returnObject.build().toString();

    }
}
