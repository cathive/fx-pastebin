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

import javafx.application.Application;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URI;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Benjamin P. Jung
 */
public class PastebinApp extends Application {

    // <editor-fold desc="Property: web socket client">
    public static final String WEB_SOCKET_CLIENT_PROPERTY = "webCocketClient";
    private final ReadOnlyObjectWrapper<PasteBinWebSocketClient> webSocketClient = new ReadOnlyObjectWrapper<>(this, WEB_SOCKET_CLIENT_PROPERTY);
    public PasteBinWebSocketClient getWebSocketClient() {
        return this.webSocketClient.get();
    }
    public ReadOnlyObjectProperty<PasteBinWebSocketClient> webSocketClientProperty() {
        return this.webSocketClient.getReadOnlyProperty();
    }
    // </editor-fold>

    @FXML private ResourceBundle resources;

    @Override
    public void init() throws Exception {
        this.webSocketClient.set(new PasteBinWebSocketClient(URI.create("http://localhost:8080/fx-pastebin")));
        super.init();
    }

    @Override
    public void start(final Stage primaryStage) throws Exception {

        final FXMLLoader loader = new FXMLLoader(getClass().getResource("PastebinApp.fxml"));
        loader.setResources(ResourceBundle.getBundle(Messages.class.getName()));
        loader.setRoot(this);
        loader.setController(this);
        loader.load();

        primaryStage.setTitle(this.resources.getString(Messages.APP_TITLE));

        final Scene scene = new Scene(new RootPane());

        primaryStage.setScene(scene);

        primaryStage.show();

    }

    public void initialize() {

    }

    public static void main(final String... args) {
        Application.launch(PastebinApp.class, args);
    }

}
