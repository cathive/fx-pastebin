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

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ResourceBundle;

/**
 * @author Benjamin P. Jung
 */
public class RootPane extends VBox {

    private final PastebinApp app;

    public RootPane(final PastebinApp app) {

        super();

        this.app = app;

        final FXMLLoader fxmlLoader = new FXMLLoader(
                this.getClass().getResource("RootPane.fxml"),
                ResourceBundle.getBundle(Messages.class.getName())
        );
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (final IOException e) {
            throw new RuntimeException("Couldn't construct root pane.", e);
        }

    }

    @FXML
    public void performRefresh(final ActionEvent event) {
        this.app.getRestConnection().getRecentPastes().getPaste().forEach((p) -> {
            System.out.println(p.getId() + " " + p.getContent());
        });
    }

}
