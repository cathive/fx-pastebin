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
import com.cathive.fx.pastebin.common.rest.conversion.common.AbstractCollectionMessageBodyReader;

import javax.json.*;
import javax.ws.rs.Consumes;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Benjamin P. Jung
 */
@Provider
@Consumes(MediaType.APPLICATION_JSON)
public class PasteTypeCollectionMessageBodyReader extends AbstractCollectionMessageBodyReader<PasteType> {

    public PasteTypeCollectionMessageBodyReader() {
        super(PasteType.class);
    }


    @Override
    public Collection<PasteType> readFrom(Class<Collection<PasteType>> type, Type genericType, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, String> httpHeaders, InputStream entityStream) throws IOException, WebApplicationException {
        final List pasteTypes = new ArrayList<>();
        final JsonReader jsonReader = Json.createReader(entityStream);
        final JsonArray jsonArray = jsonReader.readArray();
        try {
            for (final JsonValue jsonValue: jsonArray) {
                pasteTypes.add(this.getEntityMessageBodyReader(annotations, mediaType).read(jsonValue));
            }
        } catch (final IOException e) {
            throw new IllegalStateException(e);
        }
        return pasteTypes;
    }

}
