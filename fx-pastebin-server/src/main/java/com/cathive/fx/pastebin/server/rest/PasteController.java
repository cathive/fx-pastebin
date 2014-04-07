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

import com.cathive.fx.pastebin.common.model.Paste;
import com.cathive.fx.pastebin.server.service.PasteService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;

import static javax.ws.rs.core.Response.*;

/**
 * Provides an external REST interface for {@link com.cathive.fx.pastebin.common.model.Paste} instances.
 *
 * @author Alexander Erben
 * @author Benjamin P. Jung
 */
@Path("/pastes")
@Produces(MediaType.APPLICATION_JSON)
public class PasteController {

    public static final String CREATED_URI = "/pastes/id/";

    @Inject
    private PasteService pasteService;

    /**
     * Return all {@link com.cathive.fx.pastebin.common.model.Paste}
     * References suppressed
     *
     * @return JSON
     */
    @GET
    public Collection<Paste> getAllPastes() {
        return pasteService.findAllPastes();
    }

    /**
     * Return a {@link com.cathive.fx.pastebin.common.model.Paste} by id
     * References suppressed
     *
     * @param id to search for
     * @return JSON
     */
    @GET
    @Path("/id/{id:\\d+}")
    public Response getPasteById(@PathParam("id") final Long id) {
        Paste paste = pasteService.findPasteById(id);
        if (paste != null) return ok(paste).build();
        else return noContent().build();
    }

    /**
     * Return a {@link com.cathive.fx.pastebin.common.model.Paste} by {@link com.cathive.fx.pastebin.common.model.UserProfile} id
     * References suppressed
     *
     * @param id to search for
     * @return JSON
     */
    @GET
    @Path("/userProfile/{user:\\d+}")
    public Response getPastesByUserProfile(@PathParam("user") final Long id) {
        Collection<Paste> byUser = pasteService.findPastesByUser(id);
        if (byUser != null && !byUser.isEmpty()) return ok(byUser).build();
        return noContent().build();
    }

    /**
     * Return a {@link com.cathive.fx.pastebin.common.model.Paste} by {@link com.cathive.fx.pastebin.common.model.PasteType} id
     * References suppressed
     *
     * @param id to search for
     * @return JSON
     */
    @GET
    @Path("/pasteType/{type:\\d+}")
    public Response getPastesByPasteType(@PathParam("type") final Long id) {
        Collection<Paste> byPasteType = pasteService.findPastesByUser(id);
        if (byPasteType != null && !byPasteType.isEmpty()) return ok(byPasteType).build();
        return noContent().build();
    }

    /**
     * Save or update a {@link com.cathive.fx.pastebin.common.model.Paste}
     *
     * @param paste the paste.
     * @return the saved paste.
     */
    @POST
    @Path("/userProfile/{user:\\d+}/pasteType/{type:.+}")
    public Response savePaste(Paste paste,
                              @PathParam("user") final Long userId,
                              @PathParam("type") final String typeId) throws URISyntaxException {
        Paste saved = pasteService.savePaste(paste, userId, typeId);
        return created(new URI(CREATED_URI + saved.getId())).build();
    }


    /**
     * Delete a {@link com.cathive.fx.pastebin.common.model.Paste}
     *
     * @param id the name of the entity to delete.
     * @return the deleted paste.
     */
    @DELETE
    @Path("/id/{id:\\d+}")
    public Response deletePasteType(@PathParam("id") final Long id) {
        Paste deleted = pasteService.deletePaste(id);
        if (deleted != null)
            return ok(deleted).build();
        return noContent().build();
    }

}
