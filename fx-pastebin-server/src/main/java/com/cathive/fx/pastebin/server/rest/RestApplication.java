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

package com.cathive.fx.pastebin.server.rest;

import javax.annotation.PostConstruct;
import javax.ejb.Startup;
import javax.inject.Singleton;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Representation of the RESTful application that will be deployed as Servlet by
 * the JAX-RS runtime.
 *
 * @author Benjamin P. Jung
 */
@Singleton
@Startup
@ApplicationPath("/api")
class RestApplication extends Application {

    /**
     * Logger for this class.
     */
    private final Logger LOGGER = Logger.getLogger(RestApplication.class.getName());

    @PostConstruct
    protected void initialize() {
        LOGGER.log(Level.CONFIG, "Initializing RESTful application...");
    }

}
