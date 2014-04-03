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

package com.cathive.fx.pastebin.common.rest.conversion.userprofile;

import com.cathive.fx.pastebin.common.model.UserProfile;
import com.cathive.fx.pastebin.common.rest.conversion.common.AbstractMessageBodyReader;

import javax.json.JsonObject;
import javax.json.JsonValue;
import javax.ws.rs.Consumes;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

/**
 * @author Alexander Erben
 */
@Provider
@Consumes(MediaType.APPLICATION_JSON)
public class UserProfileMessageBodyReader extends AbstractMessageBodyReader<UserProfile> {

    public UserProfileMessageBodyReader() {
        super(UserProfile.class);
    }

    @Override
    public UserProfile read(final JsonValue jsonValue) throws IOException, WebApplicationException {
        final UserProfile ret = new UserProfile();
        final JsonObject o = (JsonObject) jsonValue;
        ret.setName(o.getString("name"));
        return ret;
    }

}
