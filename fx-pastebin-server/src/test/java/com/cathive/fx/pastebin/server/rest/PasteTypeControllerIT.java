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

package com.cathive.fx.pastebin.server.rest;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.jayway.restassured.RestAssured.get;
import static org.hamcrest.CoreMatchers.hasItems;

/**
 * @author Alexander Erben (a_erben@outlook.com)
 */
@RunWith(Arquillian.class)
@RunAsClient
public class PasteTypeControllerIT {

    private static final boolean RECURSIVE = true;

    @Deployment
    public static WebArchive setupDeployment() {
        return ShrinkWrap.create(WebArchive.class, "fx-pastebin.war")
                .addPackages(RECURSIVE, "com.cathive")
                .addAsResource("META-INF/beans.xml")
                .addAsResource("META-INF/persistence.xml")
                .addAsResource("com/cathive/fx/pastebin/server/configuration/pastebinPasteTypes.properties")
                .addAsResource("orm.xml");
    }

    @Test
    public void testGetAllPasteTypes() throws Exception {
        get("/fx-pastebin/api/pasteTypes/")
                .then().assertThat()
                .body("name", hasItems("c", "d", "delphi"))
                .and()
                .body("description", hasItems("C", "D", "Delphi"));
    }
}
