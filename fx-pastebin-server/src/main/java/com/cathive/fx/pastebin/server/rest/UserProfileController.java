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
import com.cathive.fx.pastebin.common.model.UserProfile;
import com.cathive.fx.pastebin.server.server.PastebinService;

import javax.inject.Inject;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Collection;

import static java.time.format.DateTimeFormatter.ISO_DATE_TIME;
import static javax.json.Json.createArrayBuilder;
import static javax.json.Json.createObjectBuilder;

/**
 * Provides an external REST interface for {@link com.cathive.fx.pastebin.common.model.UserProfile} instances.
 * @author Alexander Erben
 */
@Path("/userProfile")
@Produces(MediaType.APPLICATION_JSON)
public class UserProfileController {

    @Inject
    private PastebinService pastebinService;

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getAllUserProfiles() {
        final Collection<UserProfile> all = pastebinService.findAllUserProfiles();
        final JsonArrayBuilder returnObject = createArrayBuilder();
        all.stream().map(this::buildUserProfileJson).forEach(returnObject::add);
        return returnObject.build().toString();
    }

    @GET
    @Path("/id/{id:\\d+}")
    public String getUserProfileById(@PathParam("id") final Long id) {
        return buildUserProfileJson(pastebinService.findUserProfileById(id)).toString();
    }

    private JsonObject buildPasteJson(final Paste p) {
        final JsonObjectBuilder singlePaste = createObjectBuilder();
        singlePaste.add("id", p.getId());
        singlePaste.add("title", p.getTitle());
        singlePaste.add("content", p.getContent());
        singlePaste.add("creation", p.getCreation().format(ISO_DATE_TIME));
        singlePaste.add("pasteType", buildPasteTypeForPaste(p));
        return singlePaste.build();
    }

    private JsonObject buildUserProfileJson(final UserProfile p) {
        final JsonObjectBuilder userBuilder = createObjectBuilder();
        userBuilder.add("id", p.getId());
        userBuilder.add("name", p.getName());
        final JsonArrayBuilder userProfiles = createArrayBuilder();
        final Collection<Paste> pastes = pastebinService.findPastesByUser(p.getId());
        pastes.stream().map(this::buildPasteJson).forEach(userProfiles::add);
        userBuilder.add("pastes", userProfiles);
        return userBuilder.build();
    }

    private JsonObject buildPasteTypeForPaste(final Paste p) {
        return createObjectBuilder()
                .add("id", p.getPasteType().getId())
                .add("name", p.getPasteType().getName())
                .build();
    }


}
