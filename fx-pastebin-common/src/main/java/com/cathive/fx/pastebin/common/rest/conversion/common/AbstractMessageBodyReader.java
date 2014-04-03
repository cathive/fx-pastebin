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

package com.cathive.fx.pastebin.common.rest.conversion.common;

import javax.json.Json;
import javax.json.JsonStructure;
import javax.json.JsonValue;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

/**
 * Common boilerplate methods for {@link javax.ws.rs.ext.MessageBodyReader}
 * @author Alexander Erben
 */
public abstract class AbstractMessageBodyReader<T> implements MessageBodyReader<T> {

    private final Class<T> t;

    protected AbstractMessageBodyReader(Class<T> type) {
        this.t = type;
    }

    @Override
    public boolean isReadable(Class<?> aClass, Type type, Annotation[] annotations, MediaType mediaType) {
        return type == t && mediaType.equals(MediaType.APPLICATION_JSON_TYPE);
    }

    @Override
    public T readFrom(Class<T> pasteClass, Type type, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, String> stringStringMultivaluedMap, InputStream inputStream) throws IOException, WebApplicationException {
        return read(Json.createReader(inputStream).read());
    }

    /**
     * This method is used to delegate the actual read and parser method to an already existing
     * JSON parser. Only one parser can be used to parse the input stream at any time, so if we want
     * to perform composition of message body readers here, we have no other choice but to do it that way.
     */
    public abstract <J extends JsonValue> T read(J jsonValue) throws IOException, WebApplicationException;

}
