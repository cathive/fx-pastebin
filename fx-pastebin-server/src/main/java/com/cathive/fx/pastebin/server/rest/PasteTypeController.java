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
import com.cathive.fx.pastebin.server.service.PasteTypeService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;

import static javax.ws.rs.core.Response.*;

/**
 * Provides an external REST interface for {@link com.cathive.fx.pastebin.common.model.PasteType} instances.
 *
 * @author Alexander Erben
 */
@Path("/pasteTypes")
@Produces(MediaType.APPLICATION_JSON)
public class PasteTypeController {

    public static final String CREATED_URI = "/pasteTypes/name/";

    @Inject
    private PasteTypeService pasteTypeService;

    /**
     * Return all {@link com.cathive.fx.pastebin.common.model.PasteType} as JSON
     *
     * @return the JSON
     */
    @GET
    public Response getAllPasteTypes() {
        Collection<PasteType> all = pasteTypeService.findAllPasteTypes();
        if (all != null && !all.isEmpty())
            return ok(all).build();
        return noContent().build();
    }

    /**
     * Retrieve a {@link com.cathive.fx.pastebin.common.model.PasteType} by name
     *
     * @param name to search for
     * @return JSON
     */
    @GET
    @Path("/name/{name:.+}")
    public Response getPasteTypeByName(@PathParam("name") final String name) {
        PasteType one = pasteTypeService.findPasteTypeByName(name);
        if (one != null)
            return ok(one).build();
        return noContent().build();
    }

    /**
     * Save or update a {@link com.cathive.fx.pastebin.common.model.PasteType}
     *
     * @param pasteType the paste type.
     * @return the saved paste type.
     */
    @POST
    public Response savePasteType(PasteType pasteType) throws URISyntaxException {
        PasteType saved = pasteTypeService.savePasteType(pasteType);
        return created(new URI(CREATED_URI + saved.getName())).build();
    }

    /**
     * Delete a {@link com.cathive.fx.pastebin.common.model.PasteType}
     *
     * @param name the name of the entity to delete.
     * @return the deleted paste type.
     */
    @DELETE
    @Path("/name/{name:.+}")
    public Response deletePasteType(@PathParam("name") final String name) {
        PasteType deleted = pasteTypeService.deletePasteType(name);
        if (deleted != null)
            return ok(deleted).build();
        return noContent().build();
    }
}