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
import com.cathive.fx.pastebin.common.rest.conversion.common.AbstractCollectionMessageBodyWriter;

import javax.json.JsonArrayBuilder;
import javax.json.JsonWriter;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Collection;

import static javax.json.Json.*;

/**
 * @author Benjamin P. Jung
 */
@Provider
@Produces(MediaType.APPLICATION_JSON)
public class UserProfileCollectionMessageBodyWriter extends AbstractCollectionMessageBodyWriter<UserProfile> {

    public UserProfileCollectionMessageBodyWriter() {
        super(UserProfile.class);
    }

    @Override
    public void writeTo(Collection<UserProfile> userProfile, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, Object> httpHeaders, OutputStream entityStream) throws IOException, WebApplicationException {
        final JsonArrayBuilder jsonBuilder = createArrayBuilder();
        userProfile.forEach(pasteType -> {
            jsonBuilder.add(createObjectBuilder()
                    .add("id", pasteType.getId())
                    .add("name", pasteType.getName()));

        });
        final JsonWriter jsonWriter = createWriter(entityStream);
        jsonWriter.write(jsonBuilder.build());
        jsonWriter.close();
    }

}
