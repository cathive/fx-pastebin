package com.cathive.fx.pastebin.common.rest.conversion.pastetype;

import com.cathive.fx.pastebin.common.model.PasteType;
import com.cathive.fx.pastebin.common.rest.conversion.common.AbstractCollectionMessageBodyWriter;

import javax.json.JsonArrayBuilder;
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
import java.util.Collection;

import static javax.json.Json.*;

/**
 * @author Benjamin P. Jung
 */
@Provider
@Produces(MediaType.APPLICATION_JSON)
public class PasteTypeCollectionMessageBodyWriter extends AbstractCollectionMessageBodyWriter<PasteType> {

    public PasteTypeCollectionMessageBodyWriter() {
        super(PasteType.class);
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
