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

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Collection;

/**
 * Provides an external REST interface for {@link com.cathive.fx.pastebin.common.model.Paste}
 * @author Benjamin P. Jung
 */
@Path("/")
@Produces({ MediaType.APPLICATION_JSON , MediaType.APPLICATION_XML })
public class PasteController {

    @Inject
    private PastebinService pastebinService;

    @GET
    @Path("pastes")
    public Collection<Paste> getAllPastes() {
        return pastebinService.findAllPastes();
    }

    @GET
    @Path("/{id:\\d+}")
    public Paste getPasteById(@PathParam("id") final Long id) {
        return pastebinService.findPasteById(id);
    }

}
