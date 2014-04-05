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

package com.cathive.fx.pastebin.common.rest.conversion.common;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.Providers;
import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.ServiceLoader;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON_TYPE;

/**
 * Common boilerplate methods for {@link javax.ws.rs.ext.MessageBodyReader} that can handle collections.
 *
 * @author Benjamin P. Jung
 */
public abstract class AbstractCollectionMessageBodyReader<T> implements MessageBodyReader<Collection<T>> {

    private static final Map<Class<?>, AbstractMessageBodyReader<?>> ENTITY_MESSAGE_BODY_READERS;

    static {
        ENTITY_MESSAGE_BODY_READERS = new HashMap<>();

        // TODO In a JavaEE / JAX-RS server context the following lines don't need to be executed!
        ServiceLoader.load(MessageBodyReader.class).forEach(r -> {
            if (r instanceof AbstractMessageBodyReader) {
                final Class<?> entityClass = (Class<?>) ((ParameterizedType) r.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
                ENTITY_MESSAGE_BODY_READERS.put(entityClass, (AbstractMessageBodyReader<?>) r);
            }
        });

    }

    @Context
    private Providers providers;

    private final Class<T> t;

    protected AbstractCollectionMessageBodyReader(Class<T> type) {
        super();
        this.t = type;
    }

    @Override
    public boolean isReadable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        if (mediaType.equals(APPLICATION_JSON_TYPE) && Collection.class.isAssignableFrom(type)) {
            final Type[] actualTypeArgs = (((ParameterizedType) genericType).getActualTypeArguments());
            return actualTypeArgs.length == 1 && actualTypeArgs[0].equals(t);
        }
        return false;
    }

    /**
     * Returns a {@link javax.ws.rs.ext.MessageBodyReader} instance from the JAX-RS context for the type
     * that is contained in the collection that this reader is able to process.
     * <p>This class is basically a workaround, because the JAX-RS <em>client</em> API
     * doesn't honor the {@link javax.ws.rs.core.Context @Context} annotation in a
     * Java SE environment.</p>
     *
     * @param annotations Annotations associated with the current invocation context.
     * @param mediaType   Media type of the requested message body reader.
     * @return A message body reader for the type of entities that are contained within
     * collections that this reader is able to process.
     */
    @SuppressWarnings("unchecked")
    protected AbstractMessageBodyReader<T> getEntityMessageBodyReader(final Annotation[] annotations, MediaType mediaType) {

        if (this.providers == null) {
            // Workaround for JavaSE JAX-RS clients that don't know anything
            // about the injected providers.
            return (AbstractMessageBodyReader<T>) ENTITY_MESSAGE_BODY_READERS.get(t);
        }

        return (AbstractMessageBodyReader<T>) this.providers.getMessageBodyReader(t, t, annotations, mediaType);
    }

}
