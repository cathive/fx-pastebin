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

package com.cathive.fx.pastebin.common.rest.conversion;

import com.cathive.fx.pastebin.common.transfer.PasteDto;
import com.cathive.fx.pastebin.common.transfer.PasteDtoBuilder;

import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import static javax.json.Json.createReader;

/**
 * @author Benjamin P. Jung
 */
@Produces(MediaType.APPLICATION_JSON)
public class PasteDtoMessageBodyReader extends AbstractDtoMessageBodyReader<PasteDto> {

    public PasteDtoMessageBodyReader() {
        super(PasteDto.class);
    }


    @Override
    public PasteDto readFrom(Class<PasteDto> type, Type genericType, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, String> httpHeaders, InputStream entityStream) throws IOException, WebApplicationException {
        return PasteDtoBuilder.create(createReader(entityStream).readObject()).build();
    }

}
