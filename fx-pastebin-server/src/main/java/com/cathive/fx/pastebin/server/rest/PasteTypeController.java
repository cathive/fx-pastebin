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
import com.cathive.fx.pastebin.common.model.PasteType;
import com.cathive.fx.pastebin.server.service.PastebinService;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.Providers;
import java.util.Collection;

/**
 * Provides an external REST interface for {@link com.cathive.fx.pastebin.common.model.PasteType} instances.
 *
 * @author Alexander Erben
 */
@Path("/pasteType")
@Produces(MediaType.APPLICATION_JSON)
public class PasteTypeController {

    @Context
    private Providers providers;

    @Inject
    private PastebinService pastebinService;

    @Inject
    @Named("converter")
    private JsonConverter jsonConverter;



    /**
     * Return all {@link com.cathive.fx.pastebin.common.model.PasteType} as JSON
     * @return the JSON
     */
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Collection<PasteType> getAllPasteTypes() {
        return pastebinService.findAllPasteTypes();
    }

    /**
     * Retrieve a {@link com.cathive.fx.pastebin.common.model.PasteType} by id
     * @param id to search for
     * @return JSON
     */
    @GET
    @Path("/id/{id:\\d+}")
    public PasteType getPasteTypeById(@PathParam("id") final Long id) {
        return pastebinService.findPasteTypeById(id);
    }

}