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

import com.cathive.fx.pastebin.common.model.UserProfile;
import com.cathive.fx.pastebin.server.service.PastebinService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collection;

/**
 * Provides an external REST interface for {@link com.cathive.fx.pastebin.common.model.UserProfile} instances.
 *
 * @author Alexander Erben
 */
@Path("/userProfile")
@Produces(MediaType.APPLICATION_JSON)
public class UserProfileController {

    @Inject
    private PastebinService pastebinService;

    /**
     * Retrieve all {@link com.cathive.fx.pastebin.common.model.UserProfile} as JSON.
     *
     * @return the JSON
     */
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Collection<UserProfile> getAllUserProfiles() {
        return pastebinService.findAllUserProfiles();
    }

    /**
     * Retrieve a {@link com.cathive.fx.pastebin.common.model.UserProfile} by id as JSON
     *
     * @param id to search for
     * @return JSON
     */
    @GET
    @Path("/id/{id:\\d+}")
    public UserProfile getUserProfileById(@PathParam("id") final Long id) {
        return pastebinService.findUserProfileById(id);
    }

    @POST
    @Path("/save")
    public Response saveUserProfile(UserProfile user) {
        pastebinService.saveUserProfile(user);
        return Response.ok().build();
    }

}
