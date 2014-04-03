package com.cathive.fx.pastebin.common.rest.conversion.pastetype;

import com.cathive.fx.pastebin.common.model.PasteType;
import com.cathive.fx.pastebin.common.rest.conversion.common.AbstractMessageBodyReader;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

/**
 * @author Alexander Erben (a_erben@outlook.com)
 */
@Provider
public class PasteTypeMessageBodyReader extends AbstractMessageBodyReader<PasteType> {

    public PasteTypeMessageBodyReader() {
        super(PasteType.class);
    }

    @Override
    public PasteType readFrom(Class<PasteType> pasteClass, Type type, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, String> stringStringMultivaluedMap, InputStream inputStream) throws IOException, WebApplicationException {
        final PasteType ret = new PasteType();
        final JsonReader reader = Json.createReader(inputStream);
        final JsonObject o = reader.readObject();
        ret.setName(o.getString("name"));
        return ret;
    }
}
