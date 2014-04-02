package com.cathive.fx.pastebin.client;

import javafx.beans.NamedArg;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;

import javax.websocket.*;
import java.io.IOException;
import java.net.URI;

/**
 * @author Benjamin P. Jung
 */
@ClientEndpoint
public class WebSocketConnection {

    // <editor-fold desc="Property: endpoint URI">
    public static final String ENDPOINT_URI_PROPERTY = "endpointUri";
    private final ReadOnlyObjectWrapper<URI> endpointUri = new ReadOnlyObjectWrapper<>(this, ENDPOINT_URI_PROPERTY);
    public URI getEndpointUri() {
        return this.endpointUri.get();
    }
    public ReadOnlyObjectProperty<URI> endpointUriProperty() {
        return this.endpointUri.getReadOnlyProperty();
    }
    // </editor-fold>

    // <editor-fold desc="Property: session">
    public static final String SESSION_PROPERTY = "session";
    private final ReadOnlyObjectWrapper<Session> session = new ReadOnlyObjectWrapper<>(this, SESSION_PROPERTY);
    public Session getSession() {
        return this.session.get();
    }
    public ReadOnlyObjectProperty<Session> sessionProperty() {
        return this.session.getReadOnlyProperty();
    }
    // </editor-fold>


    public WebSocketConnection(@NamedArg(ENDPOINT_URI_PROPERTY) final URI endpointUri) {
        super();
        this.endpointUri.set(endpointUri);
    }


    public void connect(final URI endpointUri) throws IOException, DeploymentException {
        final WebSocketContainer container = ContainerProvider.getWebSocketContainer();
        container.connectToServer(this, endpointUri);
    }

    @OnOpen
    public void onOpen(final Session session) {
        assert this.session == null;
        this.session.set(session);
    }

    @OnClose
    public void onClose(final Session session, final CloseReason reason) {
        this.session.set(null);
    }

}
