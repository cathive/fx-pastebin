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

import com.cathive.fx.pastebin.common.transfer.Pastes;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Provides an external REST interface for {@link com.cathive.fx.pastebin.common.model.Paste}
 * @author Benjamin P. Jung
 */
@Path("/pastes")
@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
public class PasteController {

    @GET
    @Path("/")
    public Response getRecentPastes() {
        // TODO Return something meaningful. ;-)
        return Response.ok().build();
    }

    @GET
    @Path("/{id:\\d+}")
    public Response getPasteById(@PathParam("id") final Long id) {
        // TODO Return something meaningful. ;-)
        return Response.ok().build();
    }

}
