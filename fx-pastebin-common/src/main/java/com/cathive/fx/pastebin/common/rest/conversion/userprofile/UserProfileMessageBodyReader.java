package com.cathive.fx.pastebin.common.rest.conversion.userprofile;

import com.cathive.fx.pastebin.common.model.UserProfile;
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
public class UserProfileMessageBodyReader extends AbstractMessageBodyReader<UserProfile> {

    public UserProfileMessageBodyReader() {
        super(UserProfile.class);
    }

    @Override
    public UserProfile readFrom(Class<UserProfile> pasteClass, Type type, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, String> stringStringMultivaluedMap, InputStream inputStream) throws IOException, WebApplicationException {
        final UserProfile ret = new UserProfile();
        final JsonReader reader = Json.createReader(inputStream);
        final JsonObject o = reader.readObject();
        ret.setName(o.getString("name"));
        return ret;
    }
}
