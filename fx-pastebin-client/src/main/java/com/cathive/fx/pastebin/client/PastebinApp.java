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
import java.util.ResourceBundle;

/**
 * @author Benjamin P. Jung
 */
public class PastebinApp extends Application {

    // <editor-fold desc="Property: RESTful client">
    public static final String REST_CONNECTION_PROPERTY = "restConnection";
    private final ReadOnlyObjectWrapper<RestConnection> restConnection = new ReadOnlyObjectWrapper<>(this, REST_CONNECTION_PROPERTY);
    public RestConnection getRestConnection() {
        return this.restConnection.get();
    }
    public ReadOnlyObjectProperty<RestConnection> restConnectionProperty() {
        return this.restConnection.getReadOnlyProperty();
    }
    // </editor-fold>

    // <editor-fold desc="Property: root pane">
    public static final String ROOT_PANE_PROPERTY = "rootPane";
    private final ReadOnlyObjectWrapper<RootPane> rootPane = new ReadOnlyObjectWrapper<>(this, ROOT_PANE_PROPERTY);
    public RootPane getRootPane() {
        return this.rootPane.get();
    }
    public ReadOnlyObjectProperty<RootPane> rootPaneProperty() {
        return this.rootPane.getReadOnlyProperty();
    }
    // </editor-fold>

    @FXML private ResourceBundle resources;

    @Override
    public void init() throws Exception {
        this.restConnection.set(new RestConnection(URI.create("http://localhost:8080/fx-pastebin/api")));
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

        final Scene scene = new Scene(new RootPane(this));

        primaryStage.setScene(scene);

        primaryStage.show();

    }

    // This method will be called by the FXML Loader after all fields
    // that have been marked with @FXML have been injected.
    public void initialize() {
    }


    public static void main(final String... args) {
        Application.launch(PastebinApp.class, args);
    }

}
