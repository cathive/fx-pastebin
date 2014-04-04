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

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Benjamin P. Jung
 */
public class DataStore {

    /** Name of the persistence unit to be used when working with pastes. */
    public static final String PERSISTENCE_UNIT_NAME = "fx-pastebin-client";

    private final Map<String, String> persistenceProperties;

    public DataStore() {
        super();
        persistenceProperties = new HashMap<>();
        persistenceProperties.put("javax.persistence.jdbc.driver", "org.apache.derby.jdbc.EmbeddedDriver");
        persistenceProperties.put("javax.persistence.jdbc.url", "jdbc:derby:" + lookupLocalStorageDirectory().getAbsolutePath() + "/db;create=true");
    }

    private File lookupLocalStorageDirectory() {
        final File homeDirectory = new File(System.getProperty("user.home"));
        return new File(homeDirectory, ".cathive/Pastebin");
    }

    public EntityManager getEntityManager() {
        return Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME, persistenceProperties).createEntityManager();
    }

}