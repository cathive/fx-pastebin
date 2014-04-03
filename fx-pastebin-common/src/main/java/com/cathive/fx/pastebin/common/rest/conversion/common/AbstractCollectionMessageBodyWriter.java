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
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON_TYPE;

/**
 * Common boilerplate methods of {@link javax.ws.rs.ext.MessageBodyWriter}s
 * @author Alexander Erben
 */
public abstract class AbstractCollectionMessageBodyWriter<T> implements MessageBodyWriter<Collection<T>> {

    private final Class<T> t;

    protected AbstractCollectionMessageBodyWriter(Class<T> type) {
        this.t = type;
    }

    @Override
    public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        if (mediaType.equals(APPLICATION_JSON_TYPE) && Collection.class.isAssignableFrom(type)) {
            final Type[] actualTypeArgs = (((ParameterizedType) genericType).getActualTypeArguments());
            return actualTypeArgs.length == 1 && actualTypeArgs[0].equals(t);
        }
        return false;
    }

    @Override
    public long getSize(Collection<T> pasteTypes, Class<?> type, Type genericType, Annotation[]
            annotations, MediaType mediaType) {
        return 0; // Deprecated in JAX-RS 2
    }

}
