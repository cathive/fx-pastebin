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
import com.cathive.fx.pastebin.server.service.PasteService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Collection;

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
    public Paste getPasteById(@PathParam("id") final Long id) {
        return pasteService.findPasteById(id);
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
    public Collection<Paste> getPastesByUserProfile(@PathParam("user") final Long id) {
        return pasteService.findPastesByUser(id);
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
    public Collection<Paste> getPastesByPasteType(@PathParam("type") final Long id) {
        return pasteService.findPastesByUser(id);
    }

    /**
     * Save or update a {@link com.cathive.fx.pastebin.common.model.Paste}
     *
     * @param paste the paste.
     * @return the saved paste.
     */
    @PUT
    @Path("/save/userProfile/{user:\\d+}/pasteType/{type:\\d+}")
    public Paste savePaste(Paste paste,
                           @PathParam("user") final Long userId,
                           @PathParam("type") final String typeId) {
        return pasteService.savePaste(paste, userId, typeId);
    }

}
