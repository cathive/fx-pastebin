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
import com.cathive.fx.pastebin.server.server.PastebinService;

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
 * Provides an external REST interface for {@link com.cathive.fx.pastebin.common.model.UserProfile} instances.
 *
 * @author Alexander Erben
 */
@Path("/pasteType")
@Produces(MediaType.APPLICATION_JSON)
public class PasteTypeController {

    @Inject
    private PastebinService pastebinService;

    @Inject
    @Named("converter")
    private JsonConverter jsonConverter;

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getAllPasteTypes() {
        final JsonArrayBuilder returnObject = createArrayBuilder();
        pastebinService.findAllPasteTypes().stream().map(jsonConverter::buildPasteType).forEach(returnObject::add);
        return returnObject.build().toString();
    }

    @GET
    @Path("/id/{id:\\d+}")
    public String getPasteTypeById(@PathParam("id") final Long id) {
        return jsonConverter.buildPasteType(pastebinService.findPasteTypeById(id)).toString();
    }


}