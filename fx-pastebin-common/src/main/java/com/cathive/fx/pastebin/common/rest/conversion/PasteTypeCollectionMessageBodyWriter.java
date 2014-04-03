package com.cathive.fx.pastebin.common.rest.conversion;

import com.cathive.fx.pastebin.common.model.PasteType;

import javax.json.JsonArrayBuilder;
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
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;

import static javax.json.Json.*;

/**
 * @author Benjamin P. Jung
 */
@Provider
@Produces(MediaType.APPLICATION_JSON)
public class PasteTypeCollectionMessageBodyWriter implements MessageBodyWriter<Collection<PasteType>> {

    @Override
    public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {

        if (!mediaType.equals(MediaType.APPLICATION_JSON_TYPE)) { return false; }

        // Ensure that we're handling only List<GPSTrackerCollection> objects.
        final boolean isWritable;
        if (Collection.class.isAssignableFrom(type)) {
            final ParameterizedType parameterizedType = (ParameterizedType) genericType;
            final Type[] actualTypeArgs = (parameterizedType.getActualTypeArguments());
            isWritable = actualTypeArgs.length == 1 && actualTypeArgs[0].equals(PasteType.class);
        } else {
            isWritable = false;
        }

        return isWritable;

    }

    @Override
    public long getSize(Collection<PasteType> pasteTypes, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        // Depreacted as of JAX-RS 2.0
        return 0;
    }


    @Override
    public void writeTo(Collection<PasteType> pasteTypes, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, Object> httpHeaders, OutputStream entityStream) throws IOException, WebApplicationException {
        final JsonArrayBuilder jsonBuilder = createArrayBuilder();
        pasteTypes.forEach(pasteType -> {
            jsonBuilder.add(createObjectBuilder()
                .add("id", pasteType.getId())
                .add("name", pasteType.getName()));

        });
        final JsonWriter jsonWriter = createWriter(entityStream);
        jsonWriter.write(jsonBuilder.build());
        jsonWriter.close();
    }

}
