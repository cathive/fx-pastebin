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

import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.MessageBodyWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

/**
 * Common boilerplate methods for {@link javax.ws.rs.ext.MessageBodyWriter}
 * @author Alexander Erben
 */
public abstract class AbstractMessageBodyWriter<T> implements MessageBodyWriter<T> {

    private final Class<T> t;

    protected AbstractMessageBodyWriter(Class<T> type) {
        this.t = type;
    }

    @Override
    public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return type == t && mediaType.equals(MediaType.APPLICATION_JSON_TYPE);

    }

    @Override
    public long getSize(T pasteTypes, Class<?> type, Type genericType, Annotation[]
            annotations, MediaType mediaType) {
        return 0; // Deprecated in JAX-RS 2
    }

}
