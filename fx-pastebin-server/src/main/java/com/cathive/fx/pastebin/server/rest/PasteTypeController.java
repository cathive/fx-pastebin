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

import com.cathive.fx.pastebin.common.model.PasteType;
import com.cathive.fx.pastebin.server.service.PastebinService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Collection;

/**
 * Provides an external REST interface for {@link com.cathive.fx.pastebin.common.model.PasteType} instances.
 *
 * @author Alexander Erben
 */
@Path("/pasteTypes")
@Produces(MediaType.APPLICATION_JSON)
class PasteTypeController {

    @Inject
    private PastebinService pastebinService;

    /**
     * Return all {@link com.cathive.fx.pastebin.common.model.PasteType} as JSON
     *
     * @return the JSON
     */
    @GET
    public Collection<PasteType> getAllPasteTypes() {
        return pastebinService.findAllPasteTypes();
    }

    /**
     * Retrieve a {@link com.cathive.fx.pastebin.common.model.PasteType} by id
     *
     * @param id to search for
     * @return JSON
     */
    @GET
    @Path("/id/{id:\\d+}")
    public PasteType getPasteTypeById(@PathParam("id") final Long id) {
        return pastebinService.findPasteTypeById(id);
    }

    /**
     * Save or update a {@link com.cathive.fx.pastebin.common.model.PasteType}
     *
     * @param pasteType the paste type.
     * @return the saved paste type.
     */
    @PUT
    @Path("/save")
    public PasteType savePasteType(PasteType pasteType) {
        return pastebinService.savePasteType(pasteType);
    }
}