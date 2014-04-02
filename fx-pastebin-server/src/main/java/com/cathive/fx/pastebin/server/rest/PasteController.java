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

import com.cathive.fx.pastebin.common.transfer.*;
import com.cathive.fx.pastebin.common.transfer.PasteListDto;
import com.cathive.fx.pastebin.server.server.PastebinService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBElement;

/**
 * Provides an external REST interface for {@link com.cathive.fx.pastebin.common.model.Paste}
 * @author Benjamin P. Jung
 */
@Path("/")
@Produces({ MediaType.APPLICATION_JSON , MediaType.APPLICATION_XML })
public class PasteController {

    /** ObjectFactory to be used to wrap our JAXB types into proper XML elements. */
    private static final ObjectFactory OBJECT_FACTORY = new ObjectFactory();

    @Inject
    private PastebinService pastebinService;

    @GET
    @Path("pastes")
    public JAXBElement<PasteListDto> getAllPastes() {
        return OBJECT_FACTORY.createPastes(PasteListDtoBuilder.create(this.pastebinService.findAllPastes()).build());
    }

    @GET
    @Path("/{id:\\d+}")
    public JAXBElement<PasteDto> getPasteById(@PathParam("id") final Long id) {
        return OBJECT_FACTORY.createPaste(PasteDtoBuilder.create(this.pastebinService.findPasteById(id)).build());
    }

}
