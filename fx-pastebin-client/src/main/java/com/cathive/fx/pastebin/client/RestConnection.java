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

package com.cathive.fx.pastebin.client;

import com.cathive.fx.pastebin.common.model.PasteType;
import javafx.beans.NamedArg;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;

import javax.json.stream.JsonGenerator;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import java.net.URI;
import java.util.Collection;

/**
 * @author Benjamin P. Jung
 */
public class RestConnection {

    // <editor-fold desc="Property: endpoint URI">
    public static final String ENDPOINT_URI_PROPERTY = "endpointUri";
    private final ReadOnlyObjectWrapper<URI> endpointUri = new ReadOnlyObjectWrapper<>(this, ENDPOINT_URI_PROPERTY);
    public URI getEndpointUri() {
        return this.endpointUri.get();
    }
    protected void setEndpointUri(final URI endpointUri) {
        this.endpointUri.set(endpointUri);
    }
    public ReadOnlyObjectProperty<URI> endpointUriProperty() {
        return this.endpointUri.getReadOnlyProperty();
    }
    // </editor-fold>

    // <editor-fold desc="Property: client">
    public static final String CLIENT_PROPERTY = "client";
    private final ReadOnlyObjectWrapper<Client> client = new ReadOnlyObjectWrapper<>(this, CLIENT_PROPERTY);
    public Client getClient() {
        return this.client.get();
    }
    protected void setClient(final Client client) {
        this.client.set(client);
    }
    public ReadOnlyObjectProperty<Client> clientProperty() {
        return this.client.getReadOnlyProperty();
    }
    // </editor-fold>


    public RestConnection() {
        super();
    }

    public RestConnection(@NamedArg(ENDPOINT_URI_PROPERTY) final URI endpointUri) {

        super();
        this.setEndpointUri(endpointUri);

        try {
            final Client client = ClientBuilder.newBuilder()
                    .register(Class.forName("org.glassfish.jersey.jsonp.JsonProcessingFeature"))
                    .register(Class.forName("org.glassfish.jersey.jackson.JacksonFeature"))
                    .property(JsonGenerator.PRETTY_PRINTING, true)
                    .build();
            this.setClient(client);
        } catch (final ClassNotFoundException e) {
            throw new IllegalStateException(e);
        }

    }

    public Collection<PasteType> fetchAllPasteTypes() {
        return createRequest("pasteTypes").get(new GenericType<Collection<PasteType>>() {});
    }

    public PasteType fetchPasteTypeById(final Long id) {
        return createRequest("pastes", "id", String.valueOf(id)).get(PasteType.class);
    }

    private Invocation.Builder createRequest(final String ... pathElements) {
        WebTarget webTarget = this.getClient().target(this.getEndpointUri());
        for (final String pathElement: pathElements) {
            webTarget = webTarget.path(pathElement);
        }
        return webTarget.request(MediaType.APPLICATION_JSON_TYPE);
    }

}
