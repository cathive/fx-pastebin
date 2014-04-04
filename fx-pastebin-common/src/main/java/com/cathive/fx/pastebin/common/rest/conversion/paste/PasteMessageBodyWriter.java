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

package com.cathive.fx.pastebin.common.rest.conversion.paste;

import com.cathive.fx.pastebin.common.model.Paste;
import com.cathive.fx.pastebin.common.rest.conversion.common.AbstractMessageBodyWriter;

import javax.json.JsonObjectBuilder;
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

import static java.time.format.DateTimeFormatter.ISO_DATE_TIME;
import static javax.json.Json.createObjectBuilder;
import static javax.json.Json.createWriter;

/**
 * Writes {@link com.cathive.fx.pastebin.common.model.Paste}s
 *
 * @author Alexander Erben
 */
@Provider
@Produces(MediaType.APPLICATION_JSON)
public class PasteMessageBodyWriter extends AbstractMessageBodyWriter<Paste> {

    public PasteMessageBodyWriter() {
        super(Paste.class);
    }

    @Override
    public void writeTo(Paste p, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, Object> httpHeaders, OutputStream entityStream) throws IOException, WebApplicationException {
        final JsonObjectBuilder singlePaste = createObjectBuilder()
                .add("id", p.getId())
                .add("title", p.getTitle())
                .add("content", p.getContent())
                .add("created", p.getCreated().format(ISO_DATE_TIME))
                .add("userProfile_id", p.getUserProfile().getId())
                .add("pasteType_id", p.getPasteType().getId());
        final JsonWriter jsonWriter = createWriter(entityStream);
        jsonWriter.write(singlePaste.build());
        jsonWriter.close();
    }

}
