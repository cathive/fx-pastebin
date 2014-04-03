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

package com.cathive.fx.pastebin.common.rest.conversion.pastetype;

import com.cathive.fx.pastebin.common.model.PasteType;

import javax.json.JsonObject;
import javax.json.JsonWriter;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import static javax.json.Json.createObjectBuilder;
import static javax.json.Json.createWriter;

/**
 * Writes {@link com.cathive.fx.pastebin.common.model.PasteType}s
 * @author Alexander Erben
 */
@Provider
@Produces(MediaType.APPLICATION_JSON)
public class PasteTypeMessageBodyWriter implements MessageBodyWriter<PasteType> {

    @Override
    public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return type == PasteType.class && mediaType.equals(MediaType.APPLICATION_JSON_TYPE);
    }

    @Override
    public long getSize(PasteType pasteType, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        // Deprecated as of JAX-RS 2.0.
        return 0;
    }

    @Override
    public void writeTo(PasteType pasteType, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, Object> httpHeaders, OutputStream entityStream) throws IOException, WebApplicationException {
        final JsonObject json = createObjectBuilder()
                .add("id", pasteType.getId())
                .add("name", pasteType.getName())
                .build();
        final JsonWriter jsonWriter = createWriter(entityStream);
        jsonWriter.write(json);
        jsonWriter.close();
    }

}
