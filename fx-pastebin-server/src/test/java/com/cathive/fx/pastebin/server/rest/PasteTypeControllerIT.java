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
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.jayway.restassured.RestAssured.get;
import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import static org.jboss.shrinkwrap.api.ShrinkWrap.create;

/**
 * Integration Test for {@link com.cathive.fx.pastebin.server.rest.PasteTypeController}
 * Runs via Arquillian in a managed or embedded application server.
 * Currently configured to run on a managed JBoss Wildfly.
 *
 * @author Alexander Erben (a_erben@outlook.com)
 */
@RunWith(Arquillian.class)
@RunAsClient
public class PasteTypeControllerIT {

    private static final boolean RECURSIVE = true;

    /**
     * Add new resources or package namespaces necessary for the execution of this IT here.
     *
     * @return the readily wrapped archive.
     */
    @Deployment
    public static WebArchive setupDeployment() {
        return create(WebArchive.class, "fx-pastebin.war")
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

    @Test
    public void testFindPasteTypeByName() throws Exception {
        get("/fx-pastebin/api/pasteTypes/name/c")
                .then().assertThat()
                .body("name", is("c"))
                .and()
                .body("description", is("C"));
    }

    @Test
    public void testSavePaste() {
        given()
                .contentType("application/json")
                .body("{\"name\":\"java\",\"description\":\"Java Programming Language\"}")
                .put("/fx-pastebin/api/pasteTypes/save/")
                .then()
                .body("name", is("java"))
                .and()
                .body("description", is("Java Programming Language"));

        get("/fx-pastebin/api/pasteTypes/name/java")
                .then().assertThat()
                .body("name", is("java"))
                .and()
                .body("description", is("Java Programming Language"));
    }

    @Test
    public void testWrongUriShould404() throws Exception {
        get("/fx-pastebin/api/pasteTypes/foo").then().statusCode(404);
        get("/fx-pastebin/api/pasteTypes/id/c").then().statusCode(404);
    }
}
