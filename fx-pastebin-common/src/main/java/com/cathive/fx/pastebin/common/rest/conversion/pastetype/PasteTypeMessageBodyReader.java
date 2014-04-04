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

package com.cathive.fx.pastebin.common.rest.conversion.pastetype;

import com.cathive.fx.pastebin.common.model.PasteType;
import com.cathive.fx.pastebin.common.rest.conversion.common.AbstractMessageBodyReader;

import javax.json.JsonObject;
import javax.json.JsonValue;
import javax.ws.rs.Consumes;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.Provider;

/**
 * @author Alexander Erben
 */
@Provider
@Consumes(MediaType.APPLICATION_JSON)
public class PasteTypeMessageBodyReader extends AbstractMessageBodyReader<PasteType> {

    public PasteTypeMessageBodyReader() {
        super(PasteType.class);
    }

    @Override
    public PasteType read(final JsonValue jsonValue) throws WebApplicationException {
        final PasteType ret = new PasteType();
        final JsonObject o = (JsonObject) jsonValue;
        ret.setName(o.getString("name"));
        return ret;
    }

}
