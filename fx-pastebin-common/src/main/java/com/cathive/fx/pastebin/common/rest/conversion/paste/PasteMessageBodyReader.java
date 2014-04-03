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
import com.cathive.fx.pastebin.common.rest.conversion.common.AbstractMessageBodyReader;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.ws.rs.Consumes;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.time.LocalDateTime;

/**
 * @author Alexander Erben (a_erben@outlook.com)
 */
@Provider
@Consumes
public class PasteMessageBodyReader extends AbstractMessageBodyReader<Paste> {

    public PasteMessageBodyReader() {
        super(Paste.class);
    }

    @Override
    public Paste readFrom(Class<Paste> pasteClass, Type type, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, String> stringStringMultivaluedMap, InputStream inputStream) throws IOException, WebApplicationException {
        final Paste ret = new Paste();
        final JsonReader reader = Json.createReader(inputStream);
        final JsonObject o = reader.readObject();
        ret.setTitle(o.getString("title"));
        ret.setContent(o.getString("content"));
        ret.setCreated(LocalDateTime.parse(o.getString("created")));
        return ret;
    }

}
