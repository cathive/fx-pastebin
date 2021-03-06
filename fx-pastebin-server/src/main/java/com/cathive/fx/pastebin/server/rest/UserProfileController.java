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

import com.cathive.fx.pastebin.common.model.UserProfile;
import com.cathive.fx.pastebin.server.service.UserProfileService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Collection;

/**
 * Provides an external REST interface for {@link com.cathive.fx.pastebin.common.model.UserProfile} instances.
 *
 * @author Alexander Erben
 */
@Path("/userProfiles")
@Produces(MediaType.APPLICATION_JSON)
public class UserProfileController {

    @Inject
    private UserProfileService userProfileService;

    /**
     * Retrieve all {@link com.cathive.fx.pastebin.common.model.UserProfile} as JSON.
     *
     * @return the JSON
     */
    @GET
    public Collection<UserProfile> getAllUserProfiles() {
        return userProfileService.findAllUserProfiles();
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
        return userProfileService.findUserProfileById(id);
    }

    /**
     * Save or update a {@link com.cathive.fx.pastebin.common.model.UserProfile}
     *
     * @param user to save.
     * @return the saved user profile.
     */
    @PUT
    public UserProfile saveUserProfile(UserProfile user) {
        return userProfileService.saveUserProfile(user);
    }
}
