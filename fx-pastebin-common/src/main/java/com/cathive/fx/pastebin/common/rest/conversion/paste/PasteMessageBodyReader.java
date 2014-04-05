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

package com.cathive.fx.pastebin.common.rest.conversion.paste;

import com.cathive.fx.pastebin.common.model.Paste;
import com.cathive.fx.pastebin.common.rest.conversion.common.AbstractMessageBodyReader;

import javax.json.JsonObject;
import javax.json.JsonValue;
import javax.ws.rs.Consumes;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.Provider;
import java.time.LocalDateTime;

/**
 * @author Alexander Erben (a_erben@outlook.com)
 */
@Provider
@Consumes(MediaType.APPLICATION_JSON)
public class PasteMessageBodyReader extends AbstractMessageBodyReader<Paste> {

    public PasteMessageBodyReader() {
        super(Paste.class);
    }

    @Override
    public Paste read(final JsonValue jsonValue) throws WebApplicationException {
        final Paste ret = new Paste();
        final JsonObject o = (JsonObject) jsonValue;
        ret.setTitle(o.getString("title"));
        ret.setContent(o.getString("content"));
        ret.setCreated(LocalDateTime.parse(o.getString("created")));
        return ret;
    }

}
